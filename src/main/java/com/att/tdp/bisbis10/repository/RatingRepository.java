package com.att.tdp.bisbis10.repository;

import com.att.tdp.bisbis10.model.RatingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RatingRepository extends JpaRepository<RatingModel, Long> {

    @Query("SELECT AVG(r.rating) FROM RatingModel r WHERE r.restaurantId = :restaurantId")
    Float calculateRestaurantRating(@Param("restaurantId") Long restaurantId);

    @Query("DELETE FROM RatingModel r WHERE r.restaurantId = :restaurantId")
    void deleteRestaurantRatings(@Param("restaurantId") Long restaurantId);
}
