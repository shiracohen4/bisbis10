package com.att.tdp.bisbis10.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * Represents a restaurant entity.
 */

@Entity
@Table(name = "restaurants")
public class RestaurantModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Boolean isKosher;

    private String cuisines; // For simplicity, 'cuisines' is stored as a comma-separated string


    public RestaurantModel() {
    }

    public RestaurantModel(String name, Boolean isKosher, String cuisines) {
        this.name = name;
        this.isKosher = isKosher;
        this.cuisines = cuisines;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isKosher() {
        return isKosher;
    }

    public void setKosher(Boolean kosher) {
        isKosher = kosher;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }
}
