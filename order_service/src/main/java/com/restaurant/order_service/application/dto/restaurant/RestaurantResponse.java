package com.restaurant.order_service.application.dto.restaurant;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RestaurantResponse {

    private Long id;

    private String name;

    private String address;

    private boolean open;
}