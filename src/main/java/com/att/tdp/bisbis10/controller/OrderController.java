package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.request.OrderRequest;
import com.att.tdp.bisbis10.response.OrderResponse;
import com.att.tdp.bisbis10.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for managing order-related endpoints.
 */

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity addOrder(@RequestBody OrderRequest orderRequest) {

        Long orderId = orderService.saveOrder(orderRequest);

        return new ResponseEntity(new OrderResponse(orderId), HttpStatus.OK);
    }
}
