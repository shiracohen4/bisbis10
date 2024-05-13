package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.model.RestaurantModel;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    // Find all restaurants
    public List<RestaurantModel> findAllRestaurants() {
        return restaurantRepository.findAll();
    }

    // Find restaurant by cuisine
    public List<RestaurantModel> findRestaurantsByCuisine(String cuisine) {
        return restaurantRepository.findByCuisine(cuisine);
    }

    // Find restaurant by ID
    public Optional<RestaurantModel> findRestaurantById(Long id) {
        return restaurantRepository.findById(id);
    }

    // Save restaurant
    public RestaurantModel saveRestaurant(RestaurantModel restaurant) {
        return restaurantRepository.save(restaurant);
    }

    // Update a restaurant
    public RestaurantModel updateRestaurant(Long id, RestaurantModel updatedRestaurant) {
        Optional<RestaurantModel> existingRestaurantOptional = restaurantRepository.findById(id);
        if (existingRestaurantOptional.isPresent()) {
            RestaurantModel existingRestaurant = existingRestaurantOptional.get();
            existingRestaurant.setName(updatedRestaurant.getName());
            existingRestaurant.setKosher(updatedRestaurant.isKosher());
            existingRestaurant.setCuisines(updatedRestaurant.getCuisines());
            return restaurantRepository.save(existingRestaurant);
        } else {
            // Handle case when restaurant with given ID is not found
            throw new IllegalArgumentException("Restaurant with ID " + id + " not found");
        }
    }

    // Delete a restaurant
    public void deleteRestaurantById(Long id) {
        restaurantRepository.deleteById(id);
    }

}
