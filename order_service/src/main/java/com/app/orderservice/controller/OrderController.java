package com.app.orderservice.controller;

import com.app.orderservice.dto.OrderDto;
import com.app.orderservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "This is to fetch all Orders from Db.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched all Orders from Db",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Order not found!",
                    content = @Content),
    })
    @GetMapping(value="/orders")
    public List<OrderDto> getAllOrder() {

        log.info("Inside the getAllProduct Controller");

        return orderService.getAllOrder();
    }

    @Operation(summary = "This is to save an Order  in the Db.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Saved Order from Db.",
                    content = {@Content(mediaType = "application/json")}),
    })
    @PostMapping(value="/save")
    public OrderDto save(@RequestBody OrderDto orderDto ){
        return orderService.save(orderDto);
    }

    @Operation(summary = "This is to fetch unique Order stored in Db.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched Order form Db.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Order not found with id : orderId",
                    content = @Content),
    })
    @GetMapping("/find/{id}")
    public ResponseEntity<OrderDto> findOrderById(@PathVariable(name = "id") long orderId) {
        log.info("Inside the findOrderById Controller");
        OrderDto orderDtoResponse = orderService.findOrderById(orderId);
        return new ResponseEntity<>(orderDtoResponse, HttpStatus.OK);
    }

    @Operation(summary = "This is to update a unique Order stored in Db.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Updated Order form Db.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Order not found with id : orderId",
                    content = @Content),
    })
    @PutMapping(value="/update/{id}")
    public ResponseEntity<OrderDto> updateOrderById(@PathVariable(name = "id") long orderId, @RequestBody OrderDto orderDto) {
        log.info("Inside the updateOrderById Controller");
        OrderDto orderDtoResponse =  orderService.updateOrderById(orderId, orderDto);

        return new ResponseEntity<>(orderDtoResponse, HttpStatus.OK);
    }

    @Operation(summary = "This is to delete a unique Order stored in Db.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Order Deleted Successfully form Db.",
                    content = {@Content(mediaType = "text/plain;charset=UTF-8")}),
            @ApiResponse(responseCode = "404",
                    description = "Order not found with id : orderId",
                    content = @Content),
    })
    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable(name = "id") long orderId) {

        log.info("Inside the deleteOrderById Controller");

        orderService.deleteOrderById(orderId);

        return new ResponseEntity<>("Order Deleted Successfully", HttpStatus.OK);
    }
}
