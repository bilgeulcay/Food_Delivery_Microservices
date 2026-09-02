package com.restaurant.order_service.application.dto.menuItem;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemResponse {

    private Long id;

    private Long restaurantId;

    private String name;

    private BigDecimal price;

    private boolean available;
}