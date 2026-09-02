package com.restaurant.order_service.infrastructure.persistence.repository;

import com.restaurant.order_service.domain.model.Restaurant;
import com.restaurant.order_service.domain.repository.RestaurantRepository;
import com.restaurant.order_service.infrastructure.persistence.entity.RestaurantEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RestaurantRepositoryAdapter
        implements RestaurantRepository {

    private final JpaRestaurantRepository jpaRestaurantRepository;

    @Override
    public Restaurant save(Restaurant restaurant) {

        RestaurantEntity entity = new RestaurantEntity(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.isOpen()
        );

        RestaurantEntity saved =
                jpaRestaurantRepository.save(entity);

        return toDomain(saved);
    }

    @Override
    public Optional<Restaurant> findById(Long id) {

        return jpaRestaurantRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public List<Restaurant> findAll() {

        return jpaRestaurantRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRestaurantRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        jpaRestaurantRepository.deleteById(id);
    }

    private Restaurant toDomain(RestaurantEntity entity) {

        return new Restaurant(
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.isOpen()
        );
    }
}