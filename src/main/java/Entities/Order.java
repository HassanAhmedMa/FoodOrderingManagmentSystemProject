package Entities;

import Personchild.Customer;
import com.example.demo1.Files;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.List;
import java.util.ArrayList;
public class Order{
    private int OrderIDColumn;
    private int OrderStatus = 1; // Use an enum for better readability
    private String RestaurantName;

    private List<String> foodItemsName;
    private String foodItemsNameString ="";
    public String getFoodItemsNameString() {
        return foodItemsNameString;
    }
    public void setfoodItemsNameString()
    {
        for(FoodItem foodItem : orderedFoodItems)
        {
            foodItemsNameString = foodItemsNameString.concat(foodItem.getName() + ", ");

        }
        foodItemsNameString = foodItemsNameString.substring(0,foodItemsNameString.length()-2);
    }


    private IntegerProperty orderId = new SimpleIntegerProperty();
    public int getOrderId(){
        return orderId.get();
    }
    public void setorderId(int myInt){
        orderId.set(myInt);

    }


    public void addToHistory()
    {
        if(orderId.get() == 3)
        {
            Files.returnCustomerByName(whoOrdered).addToOrderHistory(this);

        }
    }





    public int getOrderIDColumn() {
        return OrderIDColumn;
    }

    public void setOrderIDColumn(int orderIDColumn) {
        this.OrderIDColumn = orderIDColumn;
    }

    public int getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.OrderStatus = orderStatus;
    }

    private int orderDate;
    private float orderPrice;

    public void setOrderId(int orderId) {
        this.orderId.set(orderId);
    }

    private String orderLocation;

    private List<FoodItem> orderedFoodItems=new ArrayList<>();

    public String getWhoOrdered() {
        return whoOrdered;
    }

    public void setWhoOrdered(String whoOrdered) {
        this.whoOrdered = whoOrdered;
    }

    private String whoOrdered;

public Order(int OrderIDColumn, int orderState, List<FoodItem> orderedFoodItems, String whoOrdered) {
        this.OrderIDColumn = OrderIDColumn;
        this.orderedFoodItems = orderedFoodItems;
        this.whoOrdered = whoOrdered;
        setfoodItemsNameString();

    }


    // h3mlha switch case 3l enum
//    Level myVar = Level.MEDIUM;
//
//    switch(myVar) {
//        case LOW:
//            System.out.println("PREPARING");
//            break;
//        case MEDIUM:
//            System.out.println("DELIVERING");
//            break;
//        case HIGH:
//            System.out.println("DELIVERED");
//            break;
//    }




    ////////////////////////////////////
    public int getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(int orderDate) {
        this.orderDate = orderDate;
    }
    ////////////////////////////////////
    public float getOrderPrice() {
        return orderPrice;
    }
    public void setOrderPrice(float orderPrice) {
        this.orderPrice = orderPrice;
    }
    ///////////////////////////////////
    public String getOrderLocation() {
        return orderLocation;
    }
    public void setOrderLocation(String orderLocation) {
        this.orderLocation = orderLocation;
    }
    ////////////////////////////////////

    /////////////////////////////////////
    public List<FoodItem> getOrderedFoodItems() {
        return orderedFoodItems;
    }

    // Setter for orderedFoodItems
    public void setOrderedFoodItems(List<FoodItem> orderedFoodItems) {
        this.orderedFoodItems = orderedFoodItems;
    }

    public String getOrderStatusString() {
        return orderStatusString;
    }

    public void setOrderStatusString(String orderStatusString) {
        this.orderStatusString = orderStatusString;
    }

    private String orderStatusString = OrderStatusStringUpdate();
    public void UpdateOrderStatus() {
        int TempOrderStatus = getOrderStatus();
        if(TempOrderStatus == 1){
            setOrderStatus(2);
        }
        else if(TempOrderStatus == 2){
            setOrderStatus(3);

        }

        orderStatusString =  OrderStatusStringUpdate();
    }

    public String OrderStatusStringUpdate()
    {
        int temp = getOrderStatus();
        System.out.println("TEMP IS : " + temp);
        if(temp == 1)
        {
            return "Order is being prepared";
        }
        else if(temp == 2)
        {
            return "Order is out for delivery";
        }
        else if(temp == 3)
        {
            return "Order is delivered successfully";
        }
        else {
            return "Something went wrong";
        }
    }


    public String getDeliveryingStaff() {
        return DeliveryingStaff;
    }

    public void setDeliveryingStaff(String deliveryingStaff) {
        DeliveryingStaff = deliveryingStaff;
    }

    private String DeliveryingStaff;

    /////////////////////////////////////
    /**
     *
     * @return
     */
 public float TotalOrderPrice() {
        if (orderedFoodItems==null){
            return OrderStatus;
        }
        float totalPrice = 0;
        for (FoodItem foodItem : orderedFoodItems) {
            totalPrice += foodItem.getPrice();// Sum up all food item prices
        }
        return totalPrice;
    }
}