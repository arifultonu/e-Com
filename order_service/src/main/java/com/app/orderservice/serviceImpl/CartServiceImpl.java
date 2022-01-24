package com.app.orderservice.serviceImpl;

import com.app.orderservice.entity.Cart;
import com.app.orderservice.exception.ResourceNotFoundException;
import com.app.orderservice.repository.CartRepository;
import com.app.orderservice.dto.CartDto;
import com.app.orderservice.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;

    @Override
    public List<CartDto> getAllCart() {

        List<Cart> carts = cartRepository.findAll();

        log.info("Inside getAllCart of CartService");

        return carts.stream().map(cart ->
                mapToCartDto(cart)).collect(Collectors.toList());
    }

    @Override
    public CartDto save(CartDto cartDto) {
        //business logic
        Cart cart = mapToEntity(cartDto);

        //save Shipment to DB
        Cart newCart = cartRepository.save(cart);

        //Convert Entity to Dto
        CartDto newCartDto = mapToCartDto(newCart);

        log.info("Inside createCart of CartService");

        return newCartDto;
    }


    @Override
    public CartDto findCartById(long cartId) {

        Cart cart = cartRepository.findById(cartId).orElseThrow(()-> new ResourceNotFoundException("Cart","id",cartId));

        log.info("Inside findByCartId of CartService");
        return mapToCartDto(cart);
    }

    @Override
    public CartDto updateCartById(long cartId, CartDto cartDto) {

        Cart cart = cartRepository.findById(cartId).orElseThrow(()-> new ResourceNotFoundException("Cart","id",cartId));

        cart.setCartAddDate(cartDto.getCartAddDate());
        cart.setCartSessionId(cartDto.getCartSessionId());
        cart.setCartUserId(cartDto.getCartUserId());

        Cart updatedCart = cartRepository.save(cart);

        log.info("Inside updateByCartId of CartService");

        return mapToCartDto(updatedCart);
    }

    @Override
    public void deleteCartById(long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(()-> new ResourceNotFoundException("Cart","id",cartId));

        log.info("Inside deleteByCartId of CartService");

        cartRepository.delete(cart);
    }

    public Cart mapToEntity(CartDto cartDto){
        Cart cart= new Cart();
        /*cart.setCartId(cartDto.getCartId());*/
        cart.setCartAddDate(cartDto.getCartAddDate());
        cart.setCartSessionId(cartDto.getCartSessionId());
        cart.setCartUserId(cartDto.getCartUserId());
        return cart;
    }

    private CartDto mapToCartDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setCartId(cart.getCartId());
        cartDto.setCartAddDate(cart.getCartAddDate());
        cartDto.setCartSessionId(cart.getCartSessionId());
        cartDto.setCartUserId(cart.getCartUserId());
        return cartDto;
    }
}
