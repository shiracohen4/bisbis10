package com.att.tdp.bisbis10.repository;

import com.att.tdp.bisbis10.model.RestaurantModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository interface for accessing restaurant data from the database.
 */

public interface RestaurantRepository extends JpaRepository<RestaurantModel, Long> {

    /**
     * Retrieves a list of restaurants based on the specified cuisine.
     */
    @Query("SELECT r FROM RestaurantModel r WHERE r.cuisines LIKE %:cuisine%")
    List<RestaurantModel> findByCuisine(@Param("cuisine") String cuisine);
}
