package com.att.tdp.bisbis10.model;

import jakarta.persistence.*;

/**
 * Represents an item in an order made by a customer at a restaurant.
 */

@Entity
@Table(name = "order_items")
public class OrderItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    private Long dishId;
    private Integer amount;


    public OrderItemModel() {}

    public OrderItemModel(Long orderId, Long dishId, Integer amount) {
        this.orderId = orderId;
        this.dishId = dishId;
        this.amount = amount;
    }


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
