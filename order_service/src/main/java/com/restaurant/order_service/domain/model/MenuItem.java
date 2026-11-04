package com.restaurant.order_service.domain.model;


import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

//
public class MenuItem {


    private Long id;

    private Restaurant restaurant;

    private String name;

    private BigDecimal price;

    private boolean available;

    private int stockQuantity;

    private Long version;


}
