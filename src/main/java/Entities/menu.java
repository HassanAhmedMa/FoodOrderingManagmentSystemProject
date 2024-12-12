package Entities;

import java.util.ArrayList;
import java.util.List;

public class menu {


    private List<FoodItem> foodItems;


    public menu() {
        this.foodItems = new ArrayList<>();
    }


    public void addFoodItem(FoodItem foodItem) {
        foodItems.add(foodItem);
    }


    public List<FoodItem> getFoodItemsByType(String type) {
        List<FoodItem> itemsByType = new ArrayList<>();
        for (FoodItem foodItem : foodItems) {
            if (foodItem.getType().equals(type)) {
                itemsByType.add(foodItem);
            }
        }
        return itemsByType;
    }


    public void displayMenu() {
        List<String> categories = List.of("Main Course", "Appetizer", "Dessert");

        for (String category : categories) {
            System.out.println(category + ":");
            List<FoodItem> items = getFoodItemsByType(category);
            if (items.isEmpty()) {
                System.out.println("  No items in this category.");
            } else {
                for (FoodItem item : items) {
                    System.out.println("  - " + item);
                }
            }
            System.out.println(); // Add a line break between categories
        }
    }
}





