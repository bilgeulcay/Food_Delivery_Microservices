package com.restaurant.order_service.domain.repository;

import com.restaurant.order_service.domain.model.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository {

    OrderItem save(OrderItem orderItem, Long orderId);

    Optional<OrderItem> findById(Long id);

    List<OrderItem> findByOrderId(Long orderId);

    void deleteById(Long id);
}