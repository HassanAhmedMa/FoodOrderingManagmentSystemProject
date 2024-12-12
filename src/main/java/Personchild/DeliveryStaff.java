package Personchild;

import Abstractpackages.Person;
import Entities.Order;
import java.util.List;
import java.util.ArrayList;

public class DeliveryStaff extends Person {
    private String location;
    private String reviews;
    public DeliveryStaff(String firstName, String lastName, String email, String phoneNumber ,String location , String reviews)
    {
        super(firstName,lastName,email,phoneNumber);
        this.location=location;
        this.reviews=reviews;
    }


    public void displayInfo()
    {
        System.out.println("Hello world");
    }

    
    public void assignedOrders(List<Order> orders) {
    for (Order order : orders) {
        System.out.println("Processing order: " + order.getOrderId());
        System.out.println("Order state: " + order.getOrderState());
        System.out.println("Order Total price: " + order.TotalOrderPrice());
    }

}
    public String getReviews(){return reviews;}
    public void setReviews(String reviews){this.reviews=reviews;}
    public String getLocation(){return location;}
    public void setLocation(String location){this.location=location;}
public void changeOrderStatus(List<Order> orders, int orderId)
{
    for(Order order: orders)
    {   if(order.getOrderId()==orderId) {
        Integer orderState = order.getOrderState();
        if (orderState != null && orderState == 1) { // 1:on progress
            System.out.println("on progress");
            order.setOrderState(2); // 2:on his way
            System.out.println("on his way");
        } else if (orderState == 2) {
            order.setOrderState(3);//3:delivered
            System.out.println("delivered");
        }
    }
    }
}

public void Reviews(List<Order>orders)
{   for(Order order: orders)
{
    Integer orderState = order.getOrderState();
    if(orderState==3)
    {
        System.out.println("customer review delivery staff");
    }
}

}

}
