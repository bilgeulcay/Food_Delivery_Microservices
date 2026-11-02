package com.restaurant.order_service.application.service;

import com.restaurant.order_service.application.dto.menuItem.CreateMenuItemRequest;
import com.restaurant.order_service.application.dto.menuItem.MenuItemResponse;
import com.restaurant.order_service.application.dto.menuItem.UpdateMenuItemRequest;
import com.restaurant.order_service.application.mapper.MenuItemMapper;
import com.restaurant.order_service.domain.model.MenuItem;
import com.restaurant.order_service.domain.model.Restaurant;
import com.restaurant.order_service.domain.repository.MenuItemRepository;
import com.restaurant.order_service.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuItemMapper menuItemMapper;

    public MenuItemResponse create(
            CreateMenuItemRequest request
    ) {

        Restaurant restaurant =
                restaurantRepository.findById(request.getRestaurantId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Restaurant not found"
                                ));

        MenuItem menuItem =
                menuItemMapper.toDomain(
                        request,
                        restaurant
                );

        MenuItem saved =
                menuItemRepository.save(menuItem);

        return menuItemMapper.toResponse(saved);
    }

    public MenuItemResponse getById(Long id) {

        MenuItem menuItem =
                menuItemRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Menu item not found"
                                ));

        return menuItemMapper.toResponse(menuItem);
    }

    public List<MenuItemResponse> getAll() {

        return menuItemRepository.findAll()
                .stream()
                .map(menuItemMapper::toResponse)
                .toList();
    }

    public List<MenuItemResponse> getByRestaurantId(
            Long restaurantId
    ) {

        if (!restaurantRepository.existsById(restaurantId)) {
            throw new RuntimeException(
                    "Restaurant not found"
            );
        }

        return menuItemRepository
                .findByRestaurantId(restaurantId)
                .stream()
                .map(menuItemMapper::toResponse)
                .toList();
    }

    public MenuItemResponse update(
            Long id,
            UpdateMenuItemRequest request
    ) {

        MenuItem menuItem =
                menuItemRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Menu item not found"
                                ));

        menuItemMapper.updateDomain(menuItem, request);

        MenuItem updated =
                menuItemRepository.save(menuItem);

        return menuItemMapper.toResponse(updated);
    }


    public void delete(Long id) {

        if (!menuItemRepository.findById(id).isPresent()) {
            throw new RuntimeException(
                    "Menu item not found"
            );
        }

        menuItemRepository.deleteById(id);
    }
}