package com.att.tdp.bisbis10.request;

/**
 * Represents an item in an order request.
 */

public class OrderItemRequest {

    private Long dishId;
    private int amount;


    public OrderItemRequest(Long dishId, int amount) {
        this.dishId = dishId;
        this.amount = amount;
    }


    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
