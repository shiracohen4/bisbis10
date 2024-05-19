package com.att.tdp.bisbis10.repository;

import com.att.tdp.bisbis10.model.RatingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository interface for accessing rating data from the database.
 */

public interface RatingRepository extends JpaRepository<RatingModel, Long> {

    /**
     * Calculates the average rating for a restaurant based on its ID.
     */
    @Query("SELECT AVG(r.rating) FROM RatingModel r WHERE r.restaurantId = :restaurantId")
    Float calculateRestaurantRating(@Param("restaurantId") Long restaurantId);
}
