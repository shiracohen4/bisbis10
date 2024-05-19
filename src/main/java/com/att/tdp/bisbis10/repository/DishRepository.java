package com.att.tdp.bisbis10.repository;

import com.att.tdp.bisbis10.model.DishModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository interface for accessing dish data from the database.
 */

public interface DishRepository extends JpaRepository<DishModel, Long> {

    /**
     * Finds all dishes associated with a specific restaurant.
     */
    @Query("SELECT d FROM DishModel d WHERE d.restaurantId = ?1")
    List<DishModel> findByRestaurant(@Param("restaurantId") Long restaurantId);
}
