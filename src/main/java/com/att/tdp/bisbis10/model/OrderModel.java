package com.att.tdp.bisbis10.model;

import jakarta.persistence.*;

/**
 * Represents an order made by a customer at a restaurant.
 */

@Entity
@Table(name = "orders")
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long restaurantId;


    public OrderModel() {}

    public OrderModel(Long restaurantId) {
        this.restaurantId = restaurantId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
