package com.att.tdp.bisbis10.model;

import jakarta.persistence.*;

/**
 * Represents a rating given by a customer to a restaurant.
 */

@Entity
@Table(name = "ratings")
public class RatingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long restaurantId;

    private Float rating;


    public RatingModel() {}

    public RatingModel(Long restaurantId, Float rating) {
        this.restaurantId = restaurantId;
        this.rating = rating;
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

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
