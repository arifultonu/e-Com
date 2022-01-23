package com.app.orderservice.serviceImpl;

import com.app.orderservice.entity.Cart;
import com.app.orderservice.entity.Order;
import com.app.orderservice.repository.CartRepository;
import com.app.orderservice.responseModel.CartResponseModel;

import com.app.orderservice.responseModel.OrderResponseModel;
import com.app.orderservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.getAllCarts();
    }

    @Override
    public CartResponseModel save(Cart cart) {
        //business logic
        Cart entity = cartRepository.save(cart);

        CartResponseModel model = new CartResponseModel();
        if(entity.getCartId()>0){
            model.setOutCode(String.valueOf(entity.getCartId()));
            model.setOutMessage("Save Successfully");
        }

        return model;
    }

    @Override
    public CartResponseModel update(long id, Cart cart) {
        //business logic
        Cart updateCart = cartRepository.findById(id).get();

        updateCart.setCartAddDate(cart.getCartAddDate());
        updateCart.setCartSessionId(cart.getCartSessionId());
        updateCart.setCartUserId(cart.getCartUserId());

        cartRepository.save(updateCart);

        CartResponseModel model = new CartResponseModel();
        if(updateCart.getCartId()>0){
            model.setOutCode(String.valueOf(updateCart.getCartId()));
            model.setOutMessage("Update Successfully");
        }

        return model;
    }

    @Override
    public CartResponseModel delete(long id) {
        //business logic
        Cart deleteCart = cartRepository.findById(id).get();
        cartRepository.delete(deleteCart);

        CartResponseModel model = new CartResponseModel();

        model.setOutCode(String.valueOf(id));
        model.setOutMessage("Delete Successfully");

        return model;
    }
}
