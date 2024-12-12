package Personchild;
import Abstractpackages.Person;
import java.util.ArrayList;
import java.util.List;
import Entities.*;
import java.util.Scanner;  // ana asf y hassan bs kont bgrb al class (ziad)

public class Customer extends Person {
    private String username;
    private String password;
    private List<Order> orderHistory;
    private userCart usercart;
    private userCart userCart; // hatkon temporary b7es lma al order ytlb a3rf afadyha.

    public Customer(String username, String password, String firstName, String lastName, String email, String phoneNumber) {
        super(firstName, lastName, email, phoneNumber);
        this.username = username;
        this.password = password;
        this.orderHistory = new ArrayList<>();
        this.userCart = new userCart(); // Assuming userCart is implemented elsewhere.
    }

    //customer can filter the restaurants by 2 wayss => location ya food category (ziad)
    //lazm bardo y kon lyh access 3la al menu bta3 al restaurant ashan y5tar al items w y7dd al 3dd (ziad)


    public void displayInfo() {
        System.out.println("Customer Info:");
        System.out.println("Username: " + username);
        System.out.println("Name: " + getFirstName() + " " + getLastName());
        System.out.println("Email: " + getEmail());
        System.out.println("Phone: " + getPhoneNumber());
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public List<Order> getOrderHistory() {

        return orderHistory;
    }

    public void createOrder(Restaurant restaurant) {
        Scanner scanner = new Scanner(System.in);   //   hassan lw mhndl al input ha4lha
        List<FoodItem> selectedItems = new ArrayList<>();

        System.out.println("creating order gded y3m lnas :)");
        restaurant.viewMenu(); // meeeeeen al 3aml al menu!!!!!!!

        boolean addMoreItems = true; //lw al g3an lsa g3an
        while (addMoreItems) {
            System.out.println("enter the name of the food item to add to your order:"); //hy5tar al food item mn al mneu
            String itemName = scanner.nextLine();  //   hassan lw mhndl al input ha4lha

            System.out.println("Enter the quantity:"); //hay7add al 3dd al hwa 3ayzo mn al food item al y5taro
            int quantity = scanner.nextInt();   //   hassan lw mhndl al input ha4lha
            scanner.nextLine(); // Clear the buffer     //   hassan lw mhndl al input ha4lha


            //htwl33333333333333333333333333333333333
            //using a stream (a way to process collections like lists) to find a food item from the restaurant's menu.

            //restaurant.getMenuItems()
            //Gets the list of food items from the restaurant's menu.
            //
            //
            //.stream()
            //Turns the list into a stream, which allows you to process the list item by item.
            //
            //
            //.filter(food -> food.getName().equalsIgnoreCase(itemName)):
            //Filters the items in the list. It checks if the name of each FoodItem (using food.getName()) matches the input itemName.
            //
            //
            //.findFirst():
            //After filtering, it takes the first matching item from the list (if there's one).
            //.orElse(null):
            //
            //If no matching item is found, it returns null instead of a valid FoodItem.
            FoodItem item = restaurant.getMenuItems().stream()
                    .filter(food -> food.getName().equalsIgnoreCase(itemName))
                    .findFirst()
                    .orElse(null);

            if (item != null) {
                for (int i = 0; i < quantity; i++) {
                    selectedItems.add(item);
                }
                System.out.println(itemName + " added to the order.");
            } else {
                System.out.println("Item not found. Please try again.");
            }

            System.out.println("Do you want to add more items? (yes/no)");
            String response = scanner.nextLine();            //   hassan lw mhndl al input ha4lha
            addMoreItems = response.equalsIgnoreCase("yes");
        }

        System.out.println("Enter a location for this order:");
        String orderLocation = scanner.nextLine();       //   hassan lw mhndl al input ha4lha

        Order newOrder = new Order(orderHistory.size() + 1, 0, selectedItems); // awl paramter da hay3mlna unique id
        newOrder.setOrderLocation(orderLocation); //da al location bta3 al order
        orderHistory.add(newOrder); // bd5lo fl history bta3t al orders

        System.out.println("Order created successfully!");
    }



    // cancel  order
    public void cancelOrder(int orderId) {
        // 4r7naha fo2 baa de al bt access al list ashan ams7o lw mawgod
        Order order = orderHistory.stream()
                .filter(o -> o.getOrderId() == orderId)
                .findFirst()
                .orElse(null);

        if (order != null) {
            orderHistory.remove(order); // hna hams7o
            System.out.println("order " + orderId + " has been canceled.");
        } else {
            System.out.println("Order not found or invalid ID.");
        }
    }


    // Update  order
    public void updateOrder(int orderId, Restaurant restaurant) {
        // 4r7naha fo2 baa de al bt access al list
        Order order = orderHistory.stream()
                .filter(o -> o.getOrderId() == orderId)
                .findFirst()
                .orElse(null);

        if (order != null) {
            System.out.println("updating order " + orderId );
            createOrder(restaurant); // b call method al create ashan y7sl update b3d ma y8yr al hwa 3ayzo
        } else {
            System.out.println("order not found.");
        }
    }

    // View restaurants in a specific area
//    public void viewRestaurants(List<Restaurant> restaurants, String governorate, String area) {
//        System.out.println("Restaurants in " + governorate + ", " + area + ":");
//        for (Restaurant restaurant : restaurants) {
//            if (restaurant.getGovernorate().equalsIgnoreCase(governorate) &&
//                    restaurant.getArea().(area)) {
//                System.out.println("- " + restaurant.getName() + " (" + String.join(", ", restaurant.getCategories()) + ")"); // al join de btzbt al 4kl bta3 al category bta3 al resuratnt msln httl3 kda [pizza, burger]
//            }
//        }
//    }

    // View restaurants by category
    public void viewRestaurantsByCategory(List<Restaurant> restaurants, String category) {
        System.out.println("Restaurants offering " + category + ":");
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getCategories().stream().anyMatch(cat -> cat.equalsIgnoreCase(category))) {
                System.out.println("- " + restaurant.getName() + " (" + restaurant.getGovernorate() + ", " + restaurant.getArea() + ")");
            }
        }
    }

    // View order state
    public void checkOrderState(int orderId) {
        Order order = orderHistory.stream()
                .filter(o -> o.getOrderId() == orderId)
                .findFirst()
                .orElse(null);

        if (order != null) {
            System.out.println("Order " + orderId + " state: " + order.getOrderState());
        } else {
            System.out.println("Order not found.");
        }
    }


    // View all orders
    public void viewOrders() {
        if (orderHistory.isEmpty()) {
            System.out.println("You have no orders ysa7by.");
        } else {
            System.out.println("Your orders:");
            for (Order order : orderHistory) {
                System.out.println(" Order ID: " + order.getOrderId() + ", Total Price: " + order.TotalOrderPrice() + ", Location: " + order.getOrderLocation());
            }
        }
    }
}
