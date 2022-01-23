package com.app.orderservice.controller;

import com.app.orderservice.entity.Order;
import com.app.orderservice.responseModel.OrderResponseModel;
import com.app.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-api/")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping(value="/orders")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @PostMapping(value="/save")
    public OrderResponseModel save(@RequestBody Order order){
        return orderService.save(order);
    }

    @PutMapping(value="/update/{id}")
    public OrderResponseModel update(@PathVariable long id, @RequestBody Order order){
        return orderService.update(id, order);
    }

    @DeleteMapping(value="/delete/{id}")
    public OrderResponseModel delete(@PathVariable long id){
        return orderService.delete(id);
    }
}
