package com.app.orderservice.serviceImpl;

import com.app.orderservice.entity.Order;
import com.app.orderservice.repository.OrderRepository;
import com.app.orderservice.responseModel.OrderResponseModel;
import com.app.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }


    @Override
    public OrderResponseModel save(Order order) {
        //business logic
        Order entity = orderRepository.save(order);

        OrderResponseModel model = new OrderResponseModel();
        if(entity.getOrderId()>0){
            model.setOutCode(String.valueOf(entity.getOrderId()));
            model.setOutMessage("Save Successfully");
        }

        return model;
    }

    @Override
    public OrderResponseModel update(long id, Order order) {
        //business logic
        Order updateOrder = orderRepository.findById(id).get();

        updateOrder.setOrderUserId(order.getOrderUserId());
        updateOrder.setOrderDate(order.getOrderDate());
        updateOrder.setOrderProductId(order.getOrderProductId());
        updateOrder.setOrderStatus(order.getOrderStatus());
        updateOrder.setOrderApprovalDate(order.getOrderApprovalDate());
        updateOrder.setOrderUpdateDate(order.getOrderUpdateDate());
        updateOrder.setOrderApprovedStatus(order.getOrderApprovedStatus());
        orderRepository.save(updateOrder);

        OrderResponseModel model = new OrderResponseModel();
        if(updateOrder.getOrderId()>0){
            model.setOutCode(String.valueOf(updateOrder.getOrderId()));
            model.setOutMessage("Update Successfully");
        }

        return model;
    }


    @Override
    public OrderResponseModel delete(long id) {
        //business logic
        Order deleteOrder = orderRepository.findById(id).get();
        orderRepository.delete(deleteOrder);

        OrderResponseModel model = new OrderResponseModel();

        model.setOutCode(String.valueOf(id));
        model.setOutMessage("Delete Successfully");

        return model;
    }
}
