package com.app.orderservice.service;

import com.app.orderservice.entity.Cart;

import com.app.orderservice.entity.Order;
import com.app.orderservice.responseModel.CartResponseModel;
import com.app.orderservice.responseModel.OrderResponseModel;

import java.util.List;

public interface CartService {

    List<Cart> getAllCarts();

    CartResponseModel save(Cart cart);

    CartResponseModel update(long id, Cart cart);

    CartResponseModel delete(long id);
}
