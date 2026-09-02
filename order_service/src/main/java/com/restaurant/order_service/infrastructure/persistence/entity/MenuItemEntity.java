package com.restaurant.order_service.infrastructure.persistence.entity;

import com.restaurant.order_service.domain.model.Restaurant;
import jakarta.persistence.*;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "menu_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MenuItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private RestaurantEntity restaurant;

    private String name;

    private BigDecimal price;

    private boolean available;


}
