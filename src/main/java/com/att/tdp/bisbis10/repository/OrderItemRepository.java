package com.att.tdp.bisbis10.repository;

import com.att.tdp.bisbis10.model.OrderItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for accessing order item data from the database.
 */

public interface OrderItemRepository extends JpaRepository<OrderItemModel, Long> {
}
