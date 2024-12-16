package Personchild;
import java.util.List;
import Abstractpackages.*;
import Entities.Restaurant;



// Class Admin inhertance From Person and function super is used to call constructor of class person



public class Admin extends Person {
    private List<Restaurant> allRestaurants; // Store all restaurants in a list

    public Admin(String firstName, String lastName, String email, String phoneNumber, List<Restaurant> allRestaurants) {
        super(firstName, lastName, email, phoneNumber);
        this.allRestaurants = allRestaurants;
    }

    // Function to add a restaurant
//    public void addRestaurant(String name, String governorate, String area, List<String> categories) {
//        Restaurant newRestaurant = new Restaurant(name, governorate, area, categories);
//        allRestaurants.add(newRestaurant);
//        System.out.println("New restaurant added: " + name);
//    }

    // Function to edit restaurant details
    public void editRestaurant(String name, List<Restaurant> listOfRestaurants, List<String> newLocation, List<String> newCategory) {
        boolean isUpdated = false;
        for (Restaurant restaurant : listOfRestaurants) {
            if (name.equalsIgnoreCase(restaurant.getName())) {
                restaurant.setCategories(newCategory); // Updating categories
                restaurant.setGovernorate(newLocation); // Assuming "newLocation" is a governorate update
                isUpdated = true;
                System.out.println("Restaurant " + name + " has been updated.");
                break;
            }
        }
        if (!isUpdated) {
            System.out.println("Restaurant with name " + name + " not found.");
        }
    }

    // Function to remove a restaurant
    public void removeRestaurant(String name, List<Restaurant> listOfRestaurants) {
        boolean isRemoved = false;
        for (Restaurant restaurant : listOfRestaurants) {
            if (name.equalsIgnoreCase(restaurant.getName())) {
                listOfRestaurants.remove(restaurant);
                isRemoved = true;
                System.out.println("Restaurant " + name + " has been removed.");
                break;
            }
        }
        if (!isRemoved) {
            System.out.println("Restaurant with name " + name + " not found.");
        }
    }

    public void displayInfo() {
        System.out.println("Hello, Admin " + super.getFirstName() + " " + super.getLastName());
    }
}
