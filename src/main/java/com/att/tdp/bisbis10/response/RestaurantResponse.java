package com.att.tdp.bisbis10.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a response containing details about a restaurant.
 */

public class RestaurantResponse {
    private Long id;
    private String name;
    private Float averageRating;
    private Boolean isKosher;
    private List<String> cuisines;

    public RestaurantResponse(Long id, String name, Float averageRating, Boolean isKosher, List<String> cuisines) {
        this.id = id;
        this.name = name;
        this.averageRating = averageRating;
        this.isKosher = isKosher;
        this.cuisines = cuisines;
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
}
