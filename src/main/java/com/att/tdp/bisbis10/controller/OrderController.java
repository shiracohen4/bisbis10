package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.model.OrderItemModel;
import com.att.tdp.bisbis10.request.OrderItemRequest;
import com.att.tdp.bisbis10.model.OrderModel;
import com.att.tdp.bisbis10.request.OrderRequest;
import com.att.tdp.bisbis10.response.OrderResponse;
import com.att.tdp.bisbis10.service.OrderItemService;
import com.att.tdp.bisbis10.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @Autowired
    public OrderController(OrderService orderService, OrderItemService orderItemService) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
    }

    @PostMapping
    public ResponseEntity addOrder(@RequestBody OrderRequest orderRequest) {

        // Save order
        OrderModel orderModel = new OrderModel(orderRequest.getRestaurantId());
        OrderModel savedOrder = orderService.saveOrder(orderModel);

        // Check if the new order has been saved successfully
        if (savedOrder != null) {
            // Get order ID
            Long orderId = savedOrder.getId();

            // Save order items
            List<OrderItemRequest> items = orderRequest.getItems();
            for (OrderItemRequest item : items) {
                OrderItemModel itemModel = new OrderItemModel(orderId, item.getDishId(), item.getAmount());
                OrderItemModel savedItem = orderItemService.saveOrder(itemModel);
            }

//            return ResponseEntity.ok(orderId.toString());
            return new ResponseEntity(new OrderResponse(orderId), HttpStatus.OK);
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save order.");
        }
    }
}
