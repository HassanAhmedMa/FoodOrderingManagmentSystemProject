package Personchild;
import Abstractpackages.Person;
import java.util.ArrayList;
import java.util.List;
import Entities.*;

public class Customer extends Person {
    private String username;
    private String password;

    public void setOrderHistory(List<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }
    public void addToOrderHistory(Order order) {
        this.orderHistory.add(order);
    }

    private List<Order> orderHistory;
    private userCart usercart;
    private userCart TempuserCart; // hatkon temporary b7es lma al order ytlb a3rf afadyha.
    private List<FoodItem> cart = new ArrayList<>();



    public String getGovernorate() {
        return governorate;
    }

    public void setGovernorate(String governorate) {
        this.governorate = governorate;
    }

    private String governorate;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    private String area;

    private void removeFromCart(FoodItem item)
    {
        if(item.getQuantityInCart() == 1)
        {
            cart.remove(item);
        }
    }

    public Float getTotal() {
        return total;
    }

    public void DecrementFromTotal(String foodName) {
        if(!cart.isEmpty())
        {
            for(FoodItem foodItem : cart)
            {

                if(foodItem.getName().equalsIgnoreCase(foodName))
                {
                    if(foodItem.getQuantityInCart() == 1)
                    {
                        removeFromCart(foodItem);
                        break;
                    }
                    else
                    {
                        foodItem.decrementQuantityInCart();
                        break;
                    }


                }
            }

        }
        calculateTotal();

    }
    public void IncrementFromTotal(String foodName) {
        for(FoodItem foodItem : cart)
        {
            if(foodItem.getName().equalsIgnoreCase(foodName))
            {
                foodItem.incrementQuantityInCart();
            }
        }
        calculateTotal();
    }

    private Float total = calculateTotal();
    public Float calculateTotal()
    {
        Float total = 0.0f;
        for(FoodItem item : cart )
        {
            System.out.println("FoodItem name : " + item.getName() + " Food Items price : " + item.getPrice() + "Food Items quantity : " + item.getQuantityInCart());
            total+=item.getPrice()* item.getQuantityInCart();
        }
        this.total = total;
        return total;
    }


    public void addFoodItemToCart(FoodItem fooditem)
    {
        cart.add(fooditem);
    }
    public List<FoodItem> getCart() {
        return cart;
    }

    public void setCart(List<FoodItem> cart) {
        this.cart = cart;
    }
    public boolean checkIfFoodIsInCart(FoodItem fooditem)
    {
        for(FoodItem food : cart)
        {
            if(food.getName().equals(fooditem.getName()))
            {
                return true;
            }
        }
        return false;
    }



    public Customer(String username, String password, String firstName, String lastName, String email, String phoneNumber) {
        super(firstName, lastName, email, phoneNumber);
        this.username = username;
        this.password = password;
        this.orderHistory = new ArrayList<>();
        this.TempuserCart = new userCart(); // Assuming userCart is implemented elsewhere.
    }

    //customer can filter the restaurants by 2 wayss => location ya food category (ziad)
    //lazm bardo y kon lyh access 3la al menu bta3 al restaurant ashan y5tar al items w y7dd al 3dd (ziad)


    public void displayInfo() {
        System.out.println("Customer Info:");
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
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





    // cancel  order
    public void cancelOrder(int orderId) {
        // 4r7naha fo2 baa de al bt access al list ashan ams7o lw mawgod
        Order order = orderHistory.stream()
                .filter(o -> o.getOrderIDColumn() == orderId)
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
                .filter(o -> o.getOrderIDColumn() == orderId)
                .findFirst()
                .orElse(null);

        if (order != null) {
            System.out.println("updating order " + orderId );
            //createOrder(restaurant); // b call method al create ashan y7sl update b3d ma y8yr al hwa 3ayzo
        } else {
            System.out.println("order not found.");
        }
    }

    // View restaurants in a specific area
//    public void viewRestaurants(List<Restaurant> restaurants, String governorateInterface, String area) {
//        System.out.println("Restaurants in " + governorateInterface + ", " + area + ":");
//        for (Restaurant restaurant : restaurants) {
//            if (restaurant.getGovernorate().equalsIgnoreCase(governorateInterface) &&
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
                .filter(o -> o.getOrderIDColumn() == orderId)
                .findFirst()
                .orElse(null);

        if (order != null) {
            System.out.println("Order " + orderId + " state: " + order.getOrderStatus());
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
                System.out.println(" Order ID: " + order.getOrderIDColumn() + ", Total Price: " + order.TotalOrderPrice() + ", Location: " + order.getOrderLocation());
            }
        }
    }
}
