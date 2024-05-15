package com.att.tdp.bisbis10.response;

public class OrderResponse {
    private Long orderId;

    public OrderResponse(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }
}
