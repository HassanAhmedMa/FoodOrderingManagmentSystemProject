package Entities;

import java.util.ArrayList;
import java.util.List;


public class ResturantFilter {
    private List<Restaurant> restaurants;

    // Constructor to initialize the restaurant list
    public ResturantFilter(List<Restaurant> restaurants) {
        this.restaurants = restaurants != null ? restaurants : new ArrayList<>();
    }

    // Filter restaurants by name (case-insensitive)
    public List<Restaurant> filterByName(String searchName) {
        List<Restaurant> result = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().equalsIgnoreCase(searchName)) {
                result.add(restaurant);
            }
        }
        return result;
    }

    // Filter restaurants by category (checks if the category is in the restaurant's categories list)
    public List<Restaurant> filterByCategory(String categoryToFilterOut) {
        List<Restaurant> result = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getCategories().contains(categoryToFilterOut)) {
                result.add(restaurant);
            }
        }
        return result;
    }

    // Filter restaurants by location (could be governorateInterface or area)
   // public List<Restaurant> filterByLocation(String location) {
   //     List<Restaurant> result = new ArrayList<>();
   //     for (Restaurant restaurant : restaurants) {
   //         if (restaurant.getGovernorate().equalsIgnoreCase(location) || restaurant.getArea().equalsIgnoreCase(location)) {
   //             result.add(restaurant);
   //         }
   //     }
   //     return result;
   // }
}
