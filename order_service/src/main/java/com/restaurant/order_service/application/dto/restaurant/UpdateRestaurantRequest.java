package com.restaurant.order_service.application.dto.restaurant;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateRestaurantRequest {

    private String name;

    private String address;

    private boolean open;
}