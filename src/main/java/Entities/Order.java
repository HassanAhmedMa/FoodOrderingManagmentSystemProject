package Entities;
import Personchild.Customer;

import java.util.List;
import java.util.ArrayList;
public class Order{
    private int orderId;
    private int orderDate;
    private float orderPrice;
    private String orderLocation;
    private int orderState;
    private List<FoodItem> orderedFoodItems=new ArrayList<>();
    private Customer whoOrdered;

public Order(int orderId, int orderState, List<FoodItem> orderedFoodItems) {
        this.orderId = orderId;
        this.orderState = orderState;
        this.orderedFoodItems = orderedFoodItems;
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



    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
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
    public int getOrderState() {
        return orderState;
    }
    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }
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
            return orderState;
        }
        float totalPrice = 0;
        for (FoodItem foodItem : orderedFoodItems) {
            totalPrice += foodItem.getPrice();// Sum up all food item prices
        }
        return totalPrice;
    }
}