package com.restaurant.order_service.domain.repository;


import com.restaurant.order_service.domain.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant);

    Optional<Restaurant> findById(Long id);

    List<Restaurant> findAll();

    boolean existsById(Long id);

    void deleteById(Long id);
}

