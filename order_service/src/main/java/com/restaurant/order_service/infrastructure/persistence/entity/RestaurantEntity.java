package com.restaurant.order_service.infrastructure.persistence.entity;

import com.restaurant.order_service.domain.model.MenuItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private boolean open;


}