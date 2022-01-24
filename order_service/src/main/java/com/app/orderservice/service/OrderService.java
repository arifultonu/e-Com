package com.app.orderservice.service;

import com.app.orderservice.dto.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAllOrder();

    OrderDto save(OrderDto orderDto);

    OrderDto findOrderById(long orderId);

    OrderDto updateOrderById(long orderId, OrderDto orderDto);

    void deleteOrderById(long orderId);
}
