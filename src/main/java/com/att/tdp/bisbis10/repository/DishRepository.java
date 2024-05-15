package com.att.tdp.bisbis10.repository;

import com.att.tdp.bisbis10.model.DishModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DishRepository extends JpaRepository<DishModel, Long> {

    @Query("SELECT d FROM DishModel d WHERE d.restaurantId = ?1")
    List<DishModel> findByRestaurant(@Param("restaurantId") Long restaurantId);
}
