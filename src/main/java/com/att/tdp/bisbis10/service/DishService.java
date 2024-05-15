package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.model.DishModel;
import com.att.tdp.bisbis10.request.DishRequest;
import com.att.tdp.bisbis10.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishService {

    private final DishRepository dishRepository;

    @Autowired
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    // Save a new dish
    public DishModel saveDish(DishRequest dish, Long restaurantId) {
        DishModel dishModel = new DishModel(dish.getName(), dish.getDescription(), dish.getPrice(), restaurantId);
        return dishRepository.save(dishModel);
    }

    // Update a dish
    public boolean updateDish(DishRequest dishRequest, Long dishId) {
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
            dishRepository.save(dish);
            return true; // Update successful
        } else {
            // Dish with the given ID not found
            return false;
        }
    }

//    // Update a dish
//    public boolean updateDish(Long dishId, String newName, String newDescription, BigDecimal newPrice) {
//        // Retrieve the dish by ID
//        Optional<DishModel> optionalDish = dishRepository.findById(dishId);
//
//        // Check if the dish exists
//        if (optionalDish.isPresent()) {
//            DishModel dish = optionalDish.get();
//
//            // Update the attributes
//            if (newName != null) {
//                dish.setName(newName);
//            }
//            if (newDescription != null) {
//                dish.setDescription(newDescription);
//            }
//            if (newPrice != null) {
//                dish.setPrice(newPrice);
//            }
//
//            // Save the updated dish back to the database
//            dishRepository.save(dish);
//            return true; // Update successful
//        } else {
//            // Dish with the given ID not found
//            return false;
//        }
//    }

    // Delete a dish
    public void deleteDishById(Long dishId) {
        dishRepository.deleteById(dishId);
    }

    // Find all dishes of a restaurant by ID
    public List<DishModel> findDishesByRestaurant(Long restaurantId) {
        return dishRepository.findByRestaurant(restaurantId);
    }
}
