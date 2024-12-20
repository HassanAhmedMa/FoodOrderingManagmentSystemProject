package Entities;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    public void setName(String name) {
        Name = name;
    }

    private String Name;
    //private String Location; //location da al mafrod ykon fy governmenet wl address al costumer hy 7addhom ashan ytla3lo list of all avaliable resturants (ziad )
    private List<String> governorate;
    private List<String> area =  new ArrayList<>();
    private List<String> categories = new ArrayList<>();
    private List<FoodItem> MenuItems = new ArrayList<>();
    private String imgLocation;
    public void setArea(List<String> area) {
        this.area.clear();
        this.area.addAll(area);
    }
    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    private float rating;
    public String getImgLocation() {
        return imgLocation;
    }

    public void setImgLocation(String imgLocation) {
        this.imgLocation = imgLocation;
    }



    public static boolean isRestaurantOfType(Restaurant restaurant, String type)
    {
        for(String category : restaurant.getCategories())
        {
            if(category.equalsIgnoreCase(type))
            {
                return true;
            }
        }
        return false;
    }




    /**
     * Constructor to initialize a new Entities.Restaurant object.
     *
     * @param name     The name of the restaurant.

     * @param categories The category or type of the restaurant.
     */
    public Restaurant(String name, List<String> governorate, List<String> areas, List<String> categories) {

        this.Name = name;
        this.governorate = governorate;
        for(String area : areas) {
            this.area.add(area);
        }
        this.categories = new ArrayList<>(categories);
    }


    public void removeCategory(String category) {
        this.categories.remove(category);
    }

    public void addCategory(String category) {
        this.categories.add(category);
        System.out.println("Category added: " + category);
    }

    /**
     * Gets the name of the restaurant.
     *
     * @return The name of the restaurant.
     */
    public String getName() {
        return Name;
    }
    /**





    /**
     * Gets the category of the restaurant (e.g., Fast Food, Fine Dining).
     *
     * @return The category of the restaurant.
     */
    public List<String> getCategories() {
        return categories;
    }
    /**
     * Sets the category of the restaurant.
     *
     * @param categories The new category to set for the restaurant.
     */
    public void setCategories(List<String> categories) {
        this.categories = new ArrayList<>(categories);
    }




    public void setGovernorate(List<String> governorate) {
        this.governorate = governorate;
    }

    public List<String> getGovernorate() {
        return governorate;
    }

    public List<String> getArea() {
        return area;
    }
    /**
     * Gets the list of menu items for the restaurant.
     *
     * @return The list of Entities.FoodItem objects representing the menu items of the restaurant.
     */

    public List<FoodItem> getMenuItems() {
        return MenuItems;
    }
    /**
     * Sets the list of menu items for the restaurant.
     *
     * @param menuItems The list of Entities.FoodItem objects to set as the menu items for the restaurant.
     */
    public void setMenuItems(List<FoodItem> menuItems) {
        for(FoodItem food : menuItems)
        {
            MenuItems.add(food);
        }

    };

    public void editCategory(String categoryToEdit , String newCategory) {
        categories.remove(categoryToEdit);
        categories.add(newCategory);
    }

    /**
     * Takes an object of Entities.FoodItem and adds it to the menu of the Entities.Restaurant
     *
     * @param foodItem food item to
     */
    public void addFoodItem(FoodItem foodItem) {
        FoodItem addededFoodItem = foodItem;
        MenuItems.add(addededFoodItem);
    }

    /**
     * This is a Method to Display the Menu of the Restrant
     */

    public void viewMenu() {
        if (MenuItems.isEmpty()) {
            System.out.println("The Menu is currently empty.");
        } else {
            System.out.println("Menu:");
            for (FoodItem foodItem : MenuItems) {
                System.out.println("- " + foodItem.getName() + " (" + foodItem.getType() + ") - " + foodItem.getPrice() + "$");
            }
        }

    }
    public FoodItem searchFoodItemByName(String foodName) {
        for (FoodItem foodItem : MenuItems) {
            if (foodItem.getName().equalsIgnoreCase(foodName)) {
                return foodItem; // Return the matching FoodItem
            }
        }
        return null; // Return null if no match is found
    }

    // 2. Static Method: Search for a Restaurant by Name
    public static Restaurant searchRestaurantByName(List<Restaurant> restaurants, String searchName) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().equalsIgnoreCase(searchName)) {
                return restaurant; // Return the matching Restaurant
            }
        }
        return null; // Return null if no match is found
    }
}
