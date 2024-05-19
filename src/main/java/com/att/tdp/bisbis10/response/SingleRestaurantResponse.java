package com.att.tdp.bisbis10.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a response containing details about a single restaurant, including its dishes.
 */

public class SingleRestaurantResponse {
    private Long id;
    private String name;
    private Float averageRating;
    private Boolean isKosher;
    private List<String> cuisines;
    private List<DishResponse> dishes;


    public SingleRestaurantResponse(Long id, String name, Float averageRating, Boolean isKosher, List<String> cuisines, List<DishResponse> dishes) {
        this.id = id;
        this.name = name;
        this.averageRating = averageRating;
        this.isKosher = isKosher;
        this.cuisines = cuisines;
        this.dishes = dishes;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getAverageRating() {
        return averageRating;
    }

    @JsonProperty("isKosher")
    public Boolean isKosher() {
        return isKosher;
    }

    public List<String> getCuisines() {
        return cuisines;
    }

    public List<DishResponse> getDishes() {
        return dishes;
    }
}
