package com.att.tdp.bisbis10.response;

/**
 * Represents a response containing details about an order.
 */

public class OrderResponse {
    private Long orderId;

    public OrderResponse(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }
}
