package com.restaurant.order_service.infrastructure.persistence.repository;


import com.restaurant.order_service.infrastructure.persistence.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaOrderItemRepository
        extends JpaRepository<OrderItemEntity, Long> {

    List<OrderItemEntity> findByOrder_Id(Long orderId);
}