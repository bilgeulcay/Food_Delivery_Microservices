package com.restaurant.order_service.application.mapper;
import com.restaurant.order_service.application.dto.menuItem.CreateMenuItemRequest;
import com.restaurant.order_service.application.dto.menuItem.MenuItemResponse;
import com.restaurant.order_service.application.dto.menuItem.UpdateMenuItemRequest;
import com.restaurant.order_service.domain.model.MenuItem;
import com.restaurant.order_service.domain.model.Restaurant;
import com.restaurant.order_service.infrastructure.persistence.entity.MenuItemEntity;
import com.restaurant.order_service.infrastructure.persistence.entity.RestaurantEntity;
import org.springframework.stereotype.Component;


@Component
public class MenuItemMapper {

    public MenuItem toDomain(
            CreateMenuItemRequest request,
            Restaurant restaurant
    ) {
        return new MenuItem(
                null,
                restaurant,
                request.getName(),
                request.getPrice(),
                request.isAvailable()
        );
    }

    public MenuItemResponse toResponse(MenuItem menuItem) {
        return new MenuItemResponse(
                menuItem.getId(),
                menuItem.getRestaurant().getId(),
                menuItem.getName(),
                menuItem.getPrice(),
                menuItem.isAvailable()
        );
    }

    public void updateDomain(
            MenuItem menuItem,
            UpdateMenuItemRequest request
    ) {
        menuItem.setName(request.getName());
        menuItem.setPrice(request.getPrice());
        menuItem.setAvailable(request.isAvailable());
    }
}