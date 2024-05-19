package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.exception.CustomException;
import com.att.tdp.bisbis10.model.DishModel;
import com.att.tdp.bisbis10.model.OrderItemModel;
import com.att.tdp.bisbis10.model.OrderModel;
import com.att.tdp.bisbis10.repository.DishRepository;
import com.att.tdp.bisbis10.repository.OrderItemRepository;
import com.att.tdp.bisbis10.repository.OrderRepository;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import com.att.tdp.bisbis10.request.OrderItemRequest;
import com.att.tdp.bisbis10.request.OrderRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing order-related operations.
 */

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, RestaurantRepository restaurantRepository, DishRepository dishRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.restaurantRepository = restaurantRepository;
        this.dishRepository = dishRepository;
    }

    // Save a new order
    @Transactional
    public Long saveOrder(OrderRequest orderRequest) {
        Long restaurantId = orderRequest.getRestaurantId();

        if (restaurantRepository.existsById(restaurantId)) {
            // Save order
            OrderModel orderModel = new OrderModel(restaurantId);
            OrderModel savedOrder = orderRepository.save(orderModel);

            // Get order ID
            Long orderId = savedOrder.getId();

            // Save order items only if they exist in the DB and are served at the specified restaurant
            List<DishModel> allRestaurantDishes = dishRepository.findByRestaurant(restaurantId);
            List<Long> allRestaurantDishesIds = new ArrayList<>();
            for (DishModel dish : allRestaurantDishes) {
                allRestaurantDishesIds.add(dish.getId());
            }

            List<OrderItemRequest> items = orderRequest.getItems();
            for (OrderItemRequest item : items) {
                if (dishRepository.existsById(item.getDishId())) {
                    if (allRestaurantDishesIds.contains(item.getDishId())) {
                        OrderItemModel itemModel = new OrderItemModel(orderId, item.getDishId(), item.getAmount());
                        orderItemRepository.save(itemModel);
                    }
                    else {
                        throw new CustomException("Failed to save order: Restaurant with ID " + restaurantId + " does not serve dish with ID " + item.getDishId() + ".", HttpStatus.BAD_REQUEST);
                    }
                }
                else {
                    throw new CustomException("Failed to save order: Dish with ID " + item.getDishId() + " not found.", HttpStatus.NOT_FOUND);
                }
            }
            return orderId;
        }
        else {
            throw new CustomException("Failed to save order: Restaurant with ID " + orderRequest.getRestaurantId() + " not found.", HttpStatus.NOT_FOUND);
        }
    }
}
