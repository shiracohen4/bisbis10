package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.model.OrderItemModel;
import com.att.tdp.bisbis10.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    // Save a new order item
    public OrderItemModel saveOrder(OrderItemModel orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
