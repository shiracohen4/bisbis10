package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.exception.CustomException;
import com.att.tdp.bisbis10.model.RestaurantModel;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import com.att.tdp.bisbis10.request.RestaurantRequest;
import com.att.tdp.bisbis10.response.DishResponse;
import com.att.tdp.bisbis10.response.RestaurantResponse;
import com.att.tdp.bisbis10.response.SingleRestaurantResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing restaurant-related operations.
 */

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final DishService dishService;
    private final RatingService ratingService;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, DishService dishService, RatingService ratingService) {
        this.restaurantRepository = restaurantRepository;
        this.dishService = dishService;
        this.ratingService = ratingService;
    }


    // Find all restaurants
    public List<RestaurantResponse> findAllRestaurants() {
        List<RestaurantModel> restaurantModels = restaurantRepository.findAll();
        if (restaurantModels.size() == 0) {
            throw new CustomException("No restaurants in the database yet.", HttpStatus.NOT_FOUND);
        }
        else {
            return restaurantModels.stream()
                .map(this::restModelToResponse)
                .collect(Collectors.toList());
        }
    }

    // Find restaurants by cuisine
    public List<RestaurantResponse> findRestaurantsByCuisine(String cuisine) {
        List<RestaurantModel> restaurantModels = restaurantRepository.findByCuisine(cuisine);
        if (restaurantModels.size() == 0) {
            throw new CustomException("No restaurants serving " + cuisine + " cuisine are currently available in the database.", HttpStatus.NOT_FOUND);
        }
        else {
            return restaurantModels.stream()
                    .map(this::restModelToResponse)
                    .collect(Collectors.toList());
        }
    }

    // Find restaurant by ID
    public SingleRestaurantResponse findRestaurantById(Long id) {
        Optional<RestaurantModel> optionalModel = restaurantRepository.findById(id);

        if (optionalModel.isPresent()) {
            RestaurantModel model = optionalModel.get();
            RestaurantResponse response = restModelToResponse(model);
            Float averageRating = ratingService.findRestaurantRating(model.getId());
            List<DishResponse> dishes = dishService.findDishesByRestaurant(id);

            return new SingleRestaurantResponse(
                    response.getId(),
                    response.getName(),
                    averageRating,
                    response.isKosher(),
                    response.getCuisines(),
                    dishes);
        }
        else {
            throw new CustomException("Restaurant with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    // Save restaurant
    public RestaurantModel saveRestaurant(RestaurantModel restaurant) {
        try {
            return restaurantRepository.save(restaurant);
        } catch (Exception e) {
            throw new CustomException("Failed to save the restaurant: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update a restaurant
    public RestaurantModel updateRestaurant(RestaurantRequest rRequest, Long id) {
        try {
            Optional<RestaurantModel> optionalRestaurant = restaurantRepository.findById(id);
            if (optionalRestaurant.isPresent()) {
                RestaurantModel rModel = optionalRestaurant.get();
                // Update attributes
                if (rRequest.getName() != null) {
                    rModel.setName(rRequest.getName());
                }
                if (rRequest.isKosher() != null) {
                    rModel.setKosher(rRequest.isKosher());
                }
                if (rRequest.getCuisines() != null) {
                    String cuisines = String.join(", ", rRequest.getCuisines());
                    rModel.setCuisines(cuisines);
                }
                // Save the updated restaurant back to the database
                return restaurantRepository.save(rModel);
            } else {
                // If restaurant ID is not found in the DB
                throw new CustomException("Restaurant with ID " + id + " not found.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new CustomException("Failed to update the restaurant: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete a restaurant
    public void deleteRestaurantById(Long id) {
        if (restaurantRepository.existsById(id)) {
            restaurantRepository.deleteById(id);
        } else {
            throw new CustomException("Restaurant with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    // Convert RestaurantModel to RestaurantResponse
    public RestaurantResponse restModelToResponse(RestaurantModel model) {
        Float averageRating = ratingService.findRestaurantRating(model.getId());
        List<String> cuisines = Arrays.stream(model.getCuisines()
                .replaceAll("[{}\"]", "")
                .split(","))
                .map(String::trim)
                .collect(Collectors.toList());

        return new RestaurantResponse(
                model.getId(),
                model.getName(),
                averageRating,
                model.isKosher(),
                cuisines);
    }
}
