package com.app.orderservice.serviceImpl;

import com.app.orderservice.entity.Order;
import com.app.orderservice.exception.ResourceNotFoundException;
import com.app.orderservice.repository.OrderRepository;
import com.app.orderservice.dto.OrderDto;
import com.app.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<OrderDto> getAllOrder() {

        List<Order> orders = orderRepository.findAll();

        log.info("Inside getAllShipment of ShipmentService");

        return orders.stream().map(order ->
                mapToOrderDto(order)).collect(Collectors.toList());
    }

    @Override
    public OrderDto save(OrderDto orderDto) {
        //business logic
        Order order = mapToEntity(orderDto);

        //save Shipment to DB
        Order newOrder = orderRepository.save(order);

        //Convert Entity to Dto
        OrderDto newOrderDto = mapToOrderDto(newOrder);

        log.info("Inside createOrder of OrderService");

        return newOrderDto;
    }


    @Override
    public OrderDto findOrderById(long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order","id",orderId));

        log.info("Inside findByOrderId of OrderService");
        return mapToOrderDto(order);
    }

    @Override
    public OrderDto updateOrderById(long orderId, OrderDto orderDto) {

        Order order = orderRepository.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order","id",orderId));

        order.setOrderUserId(orderDto.getOrderUserId());
        order.setOrderDate(orderDto.getOrderDate());
        order.setOrderProductId(orderDto.getOrderProductId());
        order.setOrderStatus(orderDto.getOrderStatus());
        order.setOrderApprovalDate(orderDto.getOrderApprovalDate());
        order.setOrderUpdateDate(orderDto.getOrderUpdateDate());
        order.setOrderApprovedStatus(orderDto.getOrderApprovedStatus());

        Order updatedOrder = orderRepository.save(order);

        log.info("Inside updateByOrderId of OrderService");

        return mapToOrderDto(updatedOrder);
    }

    @Override
    public void deleteOrderById(long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order","id",orderId));

        log.info("Inside deleteByOrderId of OrderService");

        orderRepository.delete(order);
    }

    public Order mapToEntity(OrderDto orderDto){
        Order order= new Order();
        /*order.setOrderId(orderDto.getOrderId());*/
        order.setOrderUserId(orderDto.getOrderUserId());
        order.setOrderDate(orderDto.getOrderDate());
        order.setOrderProductId(orderDto.getOrderProductId());
        order.setOrderStatus(orderDto.getOrderStatus());
        order.setOrderApprovalDate(orderDto.getOrderApprovalDate());
        order.setOrderUpdateDate(orderDto.getOrderUpdateDate());
        order.setOrderApprovedStatus(orderDto.getOrderApprovedStatus());

        return order;
    }

    private OrderDto mapToOrderDto(Order order) {
        OrderDto  orderDto = new  OrderDto();

        orderDto.setOrderId(order.getOrderId());
        orderDto.setOrderUserId(order.getOrderUserId());
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setOrderProductId(order.getOrderProductId());
        orderDto.setOrderStatus(order.getOrderStatus());
        orderDto.setOrderApprovalDate(order.getOrderApprovalDate());
        orderDto.setOrderUpdateDate(order.getOrderUpdateDate());
        orderDto.setOrderApprovedStatus(order.getOrderApprovedStatus());

        return orderDto;
    }
}
