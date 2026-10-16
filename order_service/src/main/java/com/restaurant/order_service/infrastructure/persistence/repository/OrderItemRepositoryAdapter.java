package com.restaurant.order_service.infrastructure.persistence.repository;


import com.restaurant.order_service.domain.model.OrderItem;
import com.restaurant.order_service.domain.repository.OrderItemRepository;
import com.restaurant.order_service.infrastructure.persistence.entity.OrderEntity;
import com.restaurant.order_service.infrastructure.persistence.entity.OrderItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderItemRepositoryAdapter implements OrderItemRepository {

    private final JpaOrderItemRepository jpaOrderItemRepository;
    private final JpaOrderRepository jpaOrderRepository;

    @Override
    public OrderItem save(OrderItem orderItem, Long orderId) {

        OrderEntity orderEntity = jpaOrderRepository.findById(orderId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Order not found: " + orderId));

        OrderItemEntity entity = new OrderItemEntity(
                orderItem.getId(),
                orderEntity,
                orderItem.getMenuItemId(),
                orderItem.getQuantity(),
                orderItem.getPriceAtOrder()
        );

        OrderItemEntity saved = jpaOrderItemRepository.save(entity);

        return toDomain(saved);
    }

    @Override
    public Optional<OrderItem> findById(Long id) {

        return jpaOrderItemRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public List<OrderItem> findByOrderId(Long orderId) {

        return jpaOrderItemRepository.findByOrder_Id(orderId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {

        jpaOrderItemRepository.deleteById(id);
    }

    private OrderItem toDomain(OrderItemEntity entity) {

        return new OrderItem(
                entity.getId(),
                entity.getMenuItemId(),
                entity.getQuantity(),
                entity.getPriceAtOrder()
        );
    }
}