package com.restaurant.order_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    private Long id;

    private String name;

    private String address;

    private boolean open;

    //private List<MenuItem> menuItems;


}

