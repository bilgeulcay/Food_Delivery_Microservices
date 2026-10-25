package com.restaurant.order_service.application.service;

import com.restaurant.order_service.application.dto.order.CreateOrderRequest;
import com.restaurant.order_service.application.dto.order.OrderResponse;
import com.restaurant.order_service.application.mapper.OrderMapper;
import com.restaurant.order_service.domain.model.MenuItem;
import com.restaurant.order_service.domain.model.Order;
import com.restaurant.order_service.domain.model.OrderItem;
import com.restaurant.order_service.domain.model.Restaurant;
import com.restaurant.order_service.domain.repository.MenuItemRepository;
import com.restaurant.order_service.domain.repository.OrderRepository;
import com.restaurant.order_service.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;
    private final OrderMapper orderMapper;

    public OrderResponse create(CreateOrderRequest request) {

        if (request.getCustomerId() == null
                || request.getRestaurantId() == null) {
            throw new IllegalArgumentException(
                    "Customer ID and restaurant ID are required"
            );
        }

        if (request.getItems() == null || request.getItems().isEmpty()) {
            throw new IllegalArgumentException(
                    "An order must contain at least one item"
            );
        }

        Restaurant restaurant = restaurantRepository
                .findById(request.getRestaurantId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Restaurant not found")
                );

        if (!restaurant.isOpen()) {
            throw new IllegalArgumentException(
                    "Restaurant is currently closed"
            );
        }

        Order order = orderMapper.toDomain(request);

        BigDecimal totalPrice = BigDecimal.ZERO;

        for (OrderItem item : order.getItems()) {

            if (item.getMenuItemId() == null || item.getQuantity() <= 0) {
                throw new IllegalArgumentException(
                        "Menu item ID is required and quantity must be positive"
                );
            }

            MenuItem menuItem = menuItemRepository
                    .findById(item.getMenuItemId())
                    .orElseThrow(() ->
                            new IllegalArgumentException("Menu item not found")
                    );

            if (!menuItem.getRestaurant().getId()
                    .equals(request.getRestaurantId())) {
                throw new IllegalArgumentException(
                        "Menu item does not belong to this restaurant"
                );
            }

            if (!menuItem.isAvailable()) {
                throw new IllegalArgumentException(
                        "Menu item is not available: " + menuItem.getName()
                );
            }

            item.setPriceAtOrder(menuItem.getPrice());

            BigDecimal itemTotal = menuItem.getPrice()
                    .multiply(BigDecimal.valueOf(item.getQuantity()));

            totalPrice = totalPrice.add(itemTotal);
        }

        order.setTotalPrice(totalPrice);
        order.setCreatedAt(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);

        return orderMapper.toResponse(savedOrder);
    }

    public OrderResponse getById(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Order not found")
                );

        return orderMapper.toResponse(order);
    }

    public List<OrderResponse> getAll() {

        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toResponse)
                .toList();
    }
}