package com.restaurant.order_service.application.mapper;


import com.restaurant.order_service.application.dto.order.CreateOrderRequest;
import com.restaurant.order_service.application.dto.order.OrderItemRequest;
import com.restaurant.order_service.application.dto.order.OrderItemResponse;
import com.restaurant.order_service.application.dto.order.OrderResponse;
import com.restaurant.order_service.domain.model.Order;
import com.restaurant.order_service.domain.model.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    public Order toDomain(CreateOrderRequest request) {

        List<OrderItem> items = request.getItems()
                .stream()
                .map(this::toDomain)
                .toList();

        return new Order(
                null,
                request.getCustomerId(),
                request.getRestaurantId(),
                "PLACED",
                null,
                null,
                items
        );
    }

    public OrderItem toDomain(OrderItemRequest request) {

        return new OrderItem(
                null,
                request.getMenuItemId(),
                request.getQuantity(),
                null
        );
    }

    public OrderResponse toResponse(Order order) {

        List<OrderItemResponse> items = order.getItems()
                .stream()
                .map(item -> new OrderItemResponse(
                        item.getId(),
                        item.getMenuItemId(),
                        item.getQuantity(),
                        item.getPriceAtOrder()
                ))
                .toList();

        return new OrderResponse(
                order.getId(),
                order.getCustomerId(),
                order.getRestaurantId(),
                order.getStatus(),
                order.getTotalPrice(),
                order.getCreatedAt(),
                items
        );
    }
}