package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.model.RatingModel;
import com.att.tdp.bisbis10.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    // Save a new rating
    public RatingModel saveRating(RatingModel rating) {
        return ratingRepository.save(rating);
    }

    // calculate average rating of a restaurant by ID
    public Float findRestaurantRating(Long restaurantId) {
        return ratingRepository.calculateRestaurantRating(restaurantId);
    }

    // Delete all ratings of a restaurant by ID
    public void deleteRestaurantRatings(Long restaurantId) {
        ratingRepository.deleteRestaurantRatings(restaurantId);
    }

}
