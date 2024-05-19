package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.model.RestaurantModel;
import com.att.tdp.bisbis10.request.RestaurantRequest;
import com.att.tdp.bisbis10.response.RestaurantResponse;
import com.att.tdp.bisbis10.response.SingleRestaurantResponse;
import com.att.tdp.bisbis10.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing restaurant operations.
 */

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }


    // Get all restaurants, or by cuisine if parameter is provided
    @GetMapping
    public ResponseEntity<List<RestaurantResponse>> getAllRestaurants(@RequestParam(required = false) String cuisine) {
        List<RestaurantResponse> restaurants;
        if (cuisine == null) {
            restaurants = restaurantService.findAllRestaurants();
        }
        else {
            restaurants = restaurantService.findRestaurantsByCuisine(cuisine);
        }
        return ResponseEntity.ok(restaurants);
    }

    // Get one restaurant
    @GetMapping("/{id}")
    ResponseEntity<SingleRestaurantResponse> getRestaurant(@PathVariable Long id) {
        SingleRestaurantResponse srResponse = restaurantService.findRestaurantById(id);
        return ResponseEntity.ok(srResponse);
    }

    // Add a restaurant
    @PostMapping
    public ResponseEntity<Void> addRestaurant(@RequestBody RestaurantRequest rRequest) {
        String cuisines = String.join(", ", rRequest.getCuisines());
        RestaurantModel rModel = new RestaurantModel(rRequest.getName(), rRequest.isKosher(), cuisines);
        restaurantService.saveRestaurant(rModel);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Update a restaurant
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRestaurant(@PathVariable Long id, @RequestBody RestaurantRequest rRequest) {
        restaurantService.updateRestaurant(rRequest, id);
        return ResponseEntity.ok().build();
    }

    // Delete a restaurant
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurantById(id);
        return ResponseEntity.noContent().build();
    }
}
