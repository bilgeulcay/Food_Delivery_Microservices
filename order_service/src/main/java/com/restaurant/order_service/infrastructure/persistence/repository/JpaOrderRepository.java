package com.restaurant.order_service.infrastructure.persistence.repository;


import com.restaurant.order_service.infrastructure.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository
        extends JpaRepository<OrderEntity, Long> {
}