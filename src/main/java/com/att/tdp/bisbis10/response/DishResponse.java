package com.att.tdp.bisbis10.response;

import java.math.BigDecimal;

/**
 * Represents a response containing details about a dish.
 */

public class DishResponse {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;


    public DishResponse(Long id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }


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
}
