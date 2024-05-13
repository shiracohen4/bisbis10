package com.att.tdp.bisbis10.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Table(name = "restaurants")
public class RestaurantModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    private double averageRating; // not sure if the rating is necessary here

    private boolean isKosher;

    // For simplicity, we're storing cuisines as a comma-separated string
    private String cuisines;

    // Constructors
    public RestaurantModel() {
    }

    public RestaurantModel(String name, boolean isKosher, String cuisines) {
        this.name = name;
        this.isKosher = isKosher;
        this.cuisines = cuisines;
    }

    // Getters and setters
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

    public boolean isKosher() {
        return isKosher;
    }

    public void setKosher(boolean kosher) {
        isKosher = kosher;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }
}
