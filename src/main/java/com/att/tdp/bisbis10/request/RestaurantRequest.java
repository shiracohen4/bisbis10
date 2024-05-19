package com.att.tdp.bisbis10.request;

import java.util.List;

/**
 * Represents a request to create or update a restaurant.
 */

public class RestaurantRequest {
    private String name;
    private Boolean isKosher;
    private List<String> cuisines;


    public RestaurantRequest(String name, Boolean isKosher, List<String> cuisines) {
        this.name = name;
        this.isKosher = isKosher;
        this.cuisines = cuisines;
    }


    public String getName() {
        return name;
    }

    public Boolean isKosher() {
        return isKosher;
    }

    public List<String> getCuisines() {
        return cuisines;
    }
}
