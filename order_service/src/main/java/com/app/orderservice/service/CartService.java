package com.app.orderservice.service;

import com.app.orderservice.dto.CartDto;

import java.util.List;

public interface CartService {
    List<CartDto> getAllCart();

    CartDto save(CartDto cartDto);

    CartDto findCartById(long cartId);

    CartDto updateCartById(long cartId, CartDto cartDto);

    void deleteCartById(long cartId);
}
