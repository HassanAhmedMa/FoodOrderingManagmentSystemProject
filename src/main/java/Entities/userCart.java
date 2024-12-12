package Entities;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import Personchild.*;



public class userCart{
    private List<FoodItem> cartItems = new ArrayList<>();
    private HashMap<FoodItem, Integer> quantity = new HashMap<>();
    private double totalPrice;

    // Get the quantity of a food item in the cart
    public int getFoodQuantity(FoodItem foodItem) {
        return quantity.getOrDefault(foodItem, 0); // Return 0 if the item isn't in the cart
    }

    // Get a food item by its name
    public FoodItem getFoodItem(String foodName) {
        for (FoodItem foodItem : cartItems) {
            if (foodName.equalsIgnoreCase(foodItem.getName())) {
                return foodItem;
            }
        }
        return null;  // Return null if the item isn't found
    }

    // Remove a food item from the cart
    public void removeFoodItemFromCart(FoodItem foodItem) {
        if (cartItems.contains(foodItem)) {
            cartItems.remove(foodItem);
            quantity.remove(foodItem);
        }
    }

    // Increase the quantity of a food item in the cart
    public void increaseQuantity(FoodItem foodItem) {
        if (!cartItems.contains(foodItem)) {
            cartItems.add(foodItem);  // Add to cartItems if it's not already there
        }
        quantity.put(foodItem, quantity.getOrDefault(foodItem, 0) + 1);  // Increase quantity
    }

    // Decrease the quantity of a food item in the cart
    public void decreaseQuantity(FoodItem foodItem) {
        if (quantity.containsKey(foodItem) && quantity.get(foodItem) > 1) {
            quantity.put(foodItem, quantity.get(foodItem) - 1);
        } else {
            cartItems.remove(foodItem);  // Remove from cartItems when quantity is 0
            quantity.remove(foodItem);
        }
    }

    // Calculate the total price of the cart based on the items and their quantities
    public void calculateTotalPrice() {
        totalPrice = 0;
        for (FoodItem foodItem : cartItems) {
            totalPrice += foodItem.getPrice() * quantity.get(foodItem);  // Multiply by quantity
        }
    }

    // Get the total price of the cart
    public double getTotalPrice() {
        return totalPrice;
    }
}