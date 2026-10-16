package com.restaurant.order_service.infrastructure.persistence.repository;


import com.restaurant.order_service.domain.model.Order;
import com.restaurant.order_service.domain.model.OrderItem;
import com.restaurant.order_service.domain.repository.OrderRepository;
import com.restaurant.order_service.infrastructure.persistence.entity.OrderEntity;
import com.restaurant.order_service.infrastructure.persistence.entity.OrderItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepository {

    private final JpaOrderRepository jpaOrderRepository;

    @Override
    public Order save(Order order) {

        OrderEntity entity = toEntity(order);

        OrderEntity saved = jpaOrderRepository.save(entity);

        return toDomain(saved);
    }

    @Override
    public Optional<Order> findById(Long id) {

        return jpaOrderRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public List<Order> findAll() {

        return jpaOrderRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {

        jpaOrderRepository.deleteById(id);
    }

    private OrderEntity toEntity(Order order) {

        OrderEntity entity = new OrderEntity(
                order.getId(),
                order.getCustomerId(),
                order.getRestaurantId(),
                order.getStatus(),
                order.getTotalPrice(),
                order.getCreatedAt(),
                null
        );

        List<OrderItemEntity> itemEntities = order.getItems()
                .stream()
                .map(item -> new OrderItemEntity(
                        item.getId(),
                        entity,
                        item.getMenuItemId(),
                        item.getQuantity(),
                        item.getPriceAtOrder()
                ))
                .toList();

        entity.setItems(itemEntities);

        return entity;
    }

    private Order toDomain(OrderEntity entity) {

        List<OrderItem> items = entity.getItems()
                .stream()
                .map(item -> new OrderItem(
                        item.getId(),
                        item.getMenuItemId(),
                        item.getQuantity(),
                        item.getPriceAtOrder()
                ))
                .toList();

        return new Order(
                entity.getId(),
                entity.getCustomerId(),
                entity.getRestaurantId(),
                entity.getStatus(),
                entity.getTotalPrice(),
                entity.getCreatedAt(),
                items
        );
    }
}