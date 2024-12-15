package Entities;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.List;
import java.util.ArrayList;
public class Order{
    private int OrderIDColumn;
    private int OrderStatus; // Use an enum for better readability


    private IntegerProperty orderId = new SimpleIntegerProperty();
    public int getOrderId(){
        return orderId.get();
    }
    public void setorderId(int myInt){
        orderId.set(myInt);

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
        this.OrderStatus = orderState;
        this.orderedFoodItems = orderedFoodItems;
        this.whoOrdered = whoOrdered;
    }
    enum status {
        PREPARING,
        DELIVERING,
        DELIVERED
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