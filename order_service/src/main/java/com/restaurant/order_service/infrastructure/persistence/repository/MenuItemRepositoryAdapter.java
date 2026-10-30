package com.restaurant.order_service.infrastructure.persistence.repository;

import com.restaurant.order_service.domain.model.MenuItem;
import com.restaurant.order_service.domain.model.Restaurant;
import com.restaurant.order_service.domain.repository.MenuItemRepository;
import com.restaurant.order_service.infrastructure.persistence.entity.MenuItemEntity;
import com.restaurant.order_service.infrastructure.persistence.entity.RestaurantEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MenuItemRepositoryAdapter
        implements MenuItemRepository {

    private final JpaMenuItemRepository jpaMenuItemRepository;

    @Override
    public MenuItem save(MenuItem menuItem) {

        Restaurant restaurant = menuItem.getRestaurant();

        RestaurantEntity restaurantEntity = new RestaurantEntity(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.isOpen()
        );

        MenuItemEntity entity = new MenuItemEntity(
                menuItem.getId(),
                restaurantEntity,
                menuItem.getName(),
                menuItem.getPrice(),
                menuItem.isAvailable(),
                menuItem.getStockQuantity()
        );

        MenuItemEntity saved =
                jpaMenuItemRepository.save(entity);

        return toDomain(saved);
    }

    @Override
    public Optional<MenuItem> findById(Long id) {

        return jpaMenuItemRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public List<MenuItem> findAll() {

        return jpaMenuItemRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<MenuItem> findByRestaurantId(Long restaurantId) {

        return jpaMenuItemRepository
                .findByRestaurant_Id(restaurantId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {

        jpaMenuItemRepository.deleteById(id);
    }

    private MenuItem toDomain(MenuItemEntity entity) {

        Restaurant restaurant = new Restaurant(
                entity.getRestaurant().getId(),
                entity.getRestaurant().getName(),
                entity.getRestaurant().getAddress(),
                entity.getRestaurant().isOpen()
        );

        return new MenuItem(
                entity.getId(),
                restaurant,
                entity.getName(),
                entity.getPrice(),
                entity.isAvailable(),
                entity.getStockQuantity()
        );
    }
}