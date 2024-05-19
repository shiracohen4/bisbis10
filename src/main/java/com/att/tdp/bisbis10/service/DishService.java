package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.exception.CustomException;
import com.att.tdp.bisbis10.model.DishModel;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import com.att.tdp.bisbis10.request.DishRequest;
import com.att.tdp.bisbis10.repository.DishRepository;
import com.att.tdp.bisbis10.response.DishResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing dish-related operations.
 */

@Service
public class DishService {

    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public DishService(DishRepository dishRepository, RestaurantRepository restaurantRepository) {
        this.dishRepository = dishRepository;
        this.restaurantRepository = restaurantRepository;
    }

    // Save a new dish
    public DishModel saveDish(DishRequest dish, Long restaurantId) {
        try {
            DishModel dishModel = new DishModel(dish.getName(), dish.getDescription(), dish.getPrice(), restaurantId);
            return dishRepository.save(dishModel);
        } catch (Exception e) {
            throw new CustomException("Failed to save the dish: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update a dish
    public DishModel updateDish(DishRequest dishRequest, Long dishId) {
        try {
            // Retrieve the dish by ID
            Optional<DishModel> optionalDish = dishRepository.findById(dishId);

            // Check if the dish exists
            if (optionalDish.isPresent()) {
                DishModel dish = optionalDish.get();

                // Update the attributes
                if (dishRequest.getName() != null) {
                    dish.setName(dishRequest.getName());
                }
                if (dishRequest.getDescription() != null) {
                    dish.setDescription(dishRequest.getDescription());
                }
                if (dishRequest.getPrice() != null) {
                    dish.setPrice(dishRequest.getPrice());
                }

                // Save the updated dish back to the database
                return dishRepository.save(dish);
            } else {
                // Dish with the given ID not found
                throw new CustomException("Dish with ID " + dishId + " not found.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new CustomException("Failed to update the dish: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete a dish
    public void deleteDishById(Long dishId, Long restaurantId) {
        if (dishRepository.existsById(dishId)) {
            if (dishRepository.findById(dishId).get().getRestaurantId().equals(restaurantId)) {
                dishRepository.deleteById(dishId);
            }
            else {
                throw new CustomException("Dish with ID " + dishId + " is not served at restaurant with ID " + restaurantId + ".", HttpStatus.NOT_FOUND);
            }
        }
        else {
            throw new CustomException("Dish with ID " + dishId + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    // Find all dishes of a restaurant by ID and convert them to DishResponse objects
    public List<DishResponse> findDishesByRestaurant(Long restaurantId) {
        if (restaurantRepository.existsById(restaurantId)) {
            List<DishModel> dishModels = dishRepository.findByRestaurant(restaurantId);
            List<DishResponse> dishes = new ArrayList<>();

            if (dishModels.size() == 0) {
                throw new CustomException("Restaurant with ID " + restaurantId + " has no dishes yet.", HttpStatus.NOT_FOUND);
            }

            for (DishModel dishModel : dishModels) {
                DishResponse dish = new DishResponse(
                        dishModel.getId(),
                        dishModel.getName(),
                        dishModel.getDescription(),
                        dishModel.getPrice());

                dishes.add(dish);
            }

            return dishes;
        }
        else {
            throw new CustomException("Restaurant with ID " + restaurantId + " not found.", HttpStatus.NOT_FOUND);
        }
    }
}
