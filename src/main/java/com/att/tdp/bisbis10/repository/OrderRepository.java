package com.att.tdp.bisbis10.repository;

import com.att.tdp.bisbis10.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for accessing order data from the database.
 */

public interface OrderRepository extends JpaRepository<OrderModel, Long> {
}
