package com.app.orderservice.controller;

import com.app.orderservice.dto.OrderDto;
import com.app.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/order-api/")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @GetMapping(value="/orders")
    public List<OrderDto> getAllOrder() {

        log.info("Inside the getAllProduct Controller");

        return orderService.getAllOrder();
    }

    @PostMapping(value="/save")
    public OrderDto save(@RequestBody OrderDto orderDto ){
        return orderService.save(orderDto);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<OrderDto> findOrderById(@PathVariable(name = "id") long orderId) {
        log.info("Inside the findOrderById Controller");
        OrderDto orderDtoResponse = orderService.findOrderById(orderId);
        return new ResponseEntity<>(orderDtoResponse, HttpStatus.OK);
    }

    @PutMapping(value="/update/{id}")
    public ResponseEntity<OrderDto> updateOrderById(@PathVariable(name = "id") long orderId, @RequestBody OrderDto orderDto) {
        log.info("Inside the updateOrderById Controller");
        OrderDto orderDtoResponse =  orderService.updateOrderById(orderId, orderDto);

        return new ResponseEntity<>(orderDtoResponse, HttpStatus.OK);
    }

    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable(name = "id") long orderId) {

        log.info("Inside the deleteOrderById Controller");

        orderService.deleteOrderById(orderId);

        return new ResponseEntity<>("Order Deleted Successfully", HttpStatus.OK);
    }
}
