package com.att.tdp.bisbis10.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "dishes")
public class DishModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Long restaurantId;

    // Constructors
    public DishModel() {}

    public DishModel(String name, String description, BigDecimal price, Long restaurantId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.restaurantId = restaurantId;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
