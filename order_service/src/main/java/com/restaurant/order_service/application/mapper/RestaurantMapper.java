package com.restaurant.order_service.application.mapper;


import com.restaurant.order_service.application.dto.restaurant.CreateRestaurantRequest;
import com.restaurant.order_service.application.dto.restaurant.RestaurantResponse;
import com.restaurant.order_service.domain.model.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper {

    public Restaurant toDomain(
            CreateRestaurantRequest request
    ) {

        return new Restaurant(
                null,
                request.getName(),
                request.getAddress(),
                true

        );
    }

    public RestaurantResponse toResponse(
            Restaurant restaurant
    ) {

        return new RestaurantResponse(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.isOpen()
        );
    }
}