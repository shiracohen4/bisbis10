package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.request.DishRequest;
import com.att.tdp.bisbis10.response.DishResponse;
import com.att.tdp.bisbis10.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing dish-related endpoints.
 */

@RestController
@RequestMapping("/restaurants/{restaurantId}/dishes")
public class DishController {

    private final DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    // Add a dish
    @PostMapping
    public ResponseEntity<Void> addDish(@PathVariable Long restaurantId, @RequestBody DishRequest dishRequest) {
        dishService.saveDish(dishRequest, restaurantId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Update a dish
    @PutMapping("/{dishId}")
    public ResponseEntity<Void> updateDish(@PathVariable Long restaurantId, @PathVariable Long dishId, @RequestBody DishRequest dishRequest) {
        dishService.updateDish(dishRequest, dishId);
        return ResponseEntity.ok().build();
    }

    // Delete a dish
    @DeleteMapping("/{dishId}")
    public ResponseEntity<Void> deleteDish(@PathVariable Long restaurantId, @PathVariable Long dishId) {
        dishService.deleteDishById(dishId, restaurantId);
        return ResponseEntity.noContent().build();
    }

    // Get dishes by a restaurant
    @GetMapping
    public ResponseEntity<List<DishResponse>> getDishesByRestaurant(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(dishService.findDishesByRestaurant(restaurantId));
    }
}
