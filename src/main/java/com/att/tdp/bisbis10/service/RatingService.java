package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.exception.CustomException;
import com.att.tdp.bisbis10.model.RatingModel;
import com.att.tdp.bisbis10.repository.RatingRepository;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Service class for managing rating-related operations.
 */

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository, RestaurantRepository restaurantRepository) {
        this.ratingRepository = ratingRepository;
        this.restaurantRepository = restaurantRepository;
    }

    // Save a new rating
    public RatingModel saveRating(RatingModel rating) {
        if (rating.getRating() >= 0 && rating.getRating() <= 5) {
            if (restaurantRepository.existsById(rating.getRestaurantId())) {
                return ratingRepository.save(rating);
            }
            else {
                throw new CustomException("Failed to save rating: Restaurant with ID " + rating.getRestaurantId() + " not found.", HttpStatus.NOT_FOUND);
            }
        }
        else {
            throw new CustomException("Failed to save rating: Rating value must be between 0 and 5.", HttpStatus.BAD_REQUEST);
        }
    }

    // Calculate average rating of a restaurant by its ID
    public Float findRestaurantRating(Long restaurantId) {
        return ratingRepository.calculateRestaurantRating(restaurantId);
    }

}
