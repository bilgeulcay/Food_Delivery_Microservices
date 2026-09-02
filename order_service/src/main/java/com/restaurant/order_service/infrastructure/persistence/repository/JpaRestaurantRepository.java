package com.restaurant.order_service.infrastructure.persistence.repository;


import com.restaurant.order_service.infrastructure.persistence.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRestaurantRepository
        extends JpaRepository<RestaurantEntity, Long> {
}