package com.restaurant.order_service.presentation.controller;


import com.restaurant.order_service.application.dto.restaurant.CreateRestaurantRequest;
import com.restaurant.order_service.application.dto.restaurant.RestaurantResponse;
import com.restaurant.order_service.application.dto.restaurant.UpdateRestaurantRequest;
import com.restaurant.order_service.application.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<RestaurantResponse> create(
            @RequestBody CreateRestaurantRequest request
    ) {

        return ResponseEntity.ok(
                restaurantService.create(request)
        );
    }

    @GetMapping
    public ResponseEntity<List<RestaurantResponse>> getAll() {

        return ResponseEntity.ok(
                restaurantService.getAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponse> getById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                restaurantService.getById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantResponse> update(
            @PathVariable Long id,
            @RequestBody UpdateRestaurantRequest request
    ) {

        return ResponseEntity.ok(
                restaurantService.update(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {

        restaurantService.delete(id);

        return ResponseEntity.noContent().build();
    }
}