package com.restaurant.order_service.application.service;

import com.restaurant.order_service.application.dto.restaurant.CreateRestaurantRequest;
import com.restaurant.order_service.application.dto.restaurant.RestaurantResponse;
import com.restaurant.order_service.application.dto.restaurant.UpdateRestaurantRequest;
import com.restaurant.order_service.application.mapper.RestaurantMapper;
import com.restaurant.order_service.domain.model.Restaurant;
import com.restaurant.order_service.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    public RestaurantResponse create(
            CreateRestaurantRequest request
    ) {

        Restaurant restaurant =
                restaurantMapper.toDomain(request);

        Restaurant saved =
                restaurantRepository.save(restaurant);

        return restaurantMapper.toResponse(saved);
    }

    public RestaurantResponse getById(Long id) {

        Restaurant restaurant =
                restaurantRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Restaurant not found"
                                ));

        return restaurantMapper.toResponse(restaurant);
    }

    public List<RestaurantResponse> getAll() {

        return restaurantRepository.findAll()
                .stream()
                .map(restaurantMapper::toResponse)
                .toList();
    }

    public RestaurantResponse update(
            Long id,
            UpdateRestaurantRequest request
    ) {

        Restaurant restaurant =
                restaurantRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Restaurant not found"
                                ));

        restaurant.setName(request.getName());
        restaurant.setAddress(request.getAddress());
        restaurant.setOpen(request.isOpen());

        Restaurant updated =
                restaurantRepository.save(restaurant);

        return restaurantMapper.toResponse(updated);
    }

    public void delete(Long id) {

        if (!restaurantRepository.existsById(id)) {
            throw new RuntimeException(
                    "Restaurant not found"
            );
        }

        restaurantRepository.deleteById(id);
    }
}
