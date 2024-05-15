package com.att.tdp.bisbis10.model;

import java.util.List;

public class OrderRequest {
    private Long restaurantId;
    private List<OrderItemRequest> orderItems;

    public OrderRequest(Long restaurantId, List<OrderItemRequest> orderItems) {
        this.restaurantId = restaurantId;
        this.orderItems = orderItems;
    }

    // Getters and setters
    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<OrderItemRequest> getItems() {
        return orderItems;
    }

    public void setItems(List<OrderItemRequest> orderItems) {
        this.orderItems = orderItems;
    }
}
