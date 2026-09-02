package com.restaurant.order_service.domain.repository;

import com.restaurant.order_service.domain.model.MenuItem;
import java.util.List;
import java.util.Optional;

public interface MenuItemRepository {

    MenuItem save(MenuItem menuItem);

    Optional<MenuItem> findById(Long id);

    List<MenuItem> findAll();

    List<MenuItem> findByRestaurantId(Long restaurantId);

    void deleteById(Long id);
}
