package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.model.OrderModel;
import com.att.tdp.bisbis10.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Save a new order
    public OrderModel saveOrder(OrderModel order) {
        return orderRepository.save(order);
    }
}
