package com.restaurant.order_service.presentation.controller;

import com.restaurant.order_service.application.dto.menuItem.CreateMenuItemRequest;
import com.restaurant.order_service.application.dto.menuItem.MenuItemResponse;
import com.restaurant.order_service.application.dto.menuItem.UpdateMenuItemRequest;
import com.restaurant.order_service.application.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu-items")
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemService menuItemService;

    @PostMapping
    public ResponseEntity<MenuItemResponse> create(
            @RequestBody CreateMenuItemRequest request
    ) {

        return ResponseEntity.ok(
                menuItemService.create(request)
        );
    }

    @GetMapping
    public ResponseEntity<List<MenuItemResponse>> getAll() {

        return ResponseEntity.ok(
                menuItemService.getAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItemResponse> getById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                menuItemService.getById(id)
        );
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<MenuItemResponse>> getByRestaurantId(
            @PathVariable Long restaurantId
    ) {

        return ResponseEntity.ok(
                menuItemService.getByRestaurantId(restaurantId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItemResponse> update(
            @PathVariable Long id,
            @RequestBody UpdateMenuItemRequest request
    ) {

        return ResponseEntity.ok(
                menuItemService.update(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {

        menuItemService.delete(id);

        return ResponseEntity.noContent().build();
    }
}