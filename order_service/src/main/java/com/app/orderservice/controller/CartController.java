package com.app.orderservice.controller;

import com.app.orderservice.entity.Cart;

import com.app.orderservice.entity.Order;
import com.app.orderservice.responseModel.CartResponseModel;
import com.app.orderservice.responseModel.OrderResponseModel;
import com.app.orderservice.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart-api/")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping(value="/carts")
    public List<Cart> getAllCarts(){
        return cartService.getAllCarts();
    }

    @PostMapping(value="/save")
    public CartResponseModel save(@RequestBody Cart cart){
        return cartService.save(cart);
    }

    @PutMapping(value="/update/{id}")
    public CartResponseModel update(@PathVariable long id, @RequestBody Cart cart){
        return cartService.update(id, cart);
    }

    @DeleteMapping(value="/delete/{id}")
    public CartResponseModel delete(@PathVariable long id){
        return cartService.delete(id);
    }

}
