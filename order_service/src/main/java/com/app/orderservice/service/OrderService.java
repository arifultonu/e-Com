package com.app.orderservice.service;

import com.app.orderservice.entity.Order;
import com.app.orderservice.responseModel.OrderResponseModel;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();

    OrderResponseModel save(Order order);

    OrderResponseModel update(long id, Order order);

    OrderResponseModel delete(long id);
}
