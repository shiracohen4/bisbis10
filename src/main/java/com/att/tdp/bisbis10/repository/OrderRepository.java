package com.att.tdp.bisbis10.repository;

import com.att.tdp.bisbis10.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {

}
