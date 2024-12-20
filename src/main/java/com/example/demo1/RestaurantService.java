package com.example.demo1;

import Entities.Restaurant;

import java.util.List;

public class RestaurantService {
    public static List<Restaurant> filterByName(List<Restaurant> restaurants, String name) {
        if (name.isEmpty()) {
            return restaurants; // Return all if no input
        }
        return restaurants.stream()
                .filter(restaurant -> restaurant.getName().toLowerCase().contains(name))
                .toList();
    }
}
