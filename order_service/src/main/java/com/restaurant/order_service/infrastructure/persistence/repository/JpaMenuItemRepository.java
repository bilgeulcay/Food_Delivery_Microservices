package com.restaurant.order_service.infrastructure.persistence.repository;


import com.restaurant.order_service.infrastructure.persistence.entity.MenuItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaMenuItemRepository
        extends JpaRepository<MenuItemEntity, Long> {

    List<MenuItemEntity> findByRestaurant_Id(Long restaurantId);
}