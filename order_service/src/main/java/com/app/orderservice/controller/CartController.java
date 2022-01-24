package com.app.orderservice.controller;


import com.app.orderservice.dto.CartDto;
import com.app.orderservice.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/cart-api/")
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping(value="/carts")
    public List<CartDto> getAllCart() {

        log.info("Inside the getAllCart Controller");

        return cartService.getAllCart();
    }

    @PostMapping(value="/save")
    public CartDto save(@RequestBody CartDto cartDto){
        return cartService.save(cartDto);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CartDto> findCartById(@PathVariable(name = "id") long cartId) {
        log.info("Inside the findCartById Controller");
        CartDto cartDtoResponse = cartService.findCartById(cartId);
        return new ResponseEntity<>(cartDtoResponse, HttpStatus.OK);
    }

    @PutMapping(value="/update/{id}")
    public ResponseEntity<CartDto> updateCartById(@PathVariable(name = "id") long cartId, @RequestBody CartDto cartDto) {
        log.info("Inside the updateCartById Controller");
        CartDto cartDtoResponse =  cartService.updateCartById(cartId, cartDto);

        return new ResponseEntity<>(cartDtoResponse, HttpStatus.OK);
    }

    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<String> deleteCartById(@PathVariable(name = "id") long cartId) {

        log.info("Inside the deleteCartById Controller");

        cartService.deleteCartById(cartId);

        return new ResponseEntity<>("Cart Deleted Successfully", HttpStatus.OK);
    }

}
