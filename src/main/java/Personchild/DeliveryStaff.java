package Personchild;

import Abstractpackages.Person;
import Entities.Order;

import java.util.ArrayList;
import java.util.List;

public class DeliveryStaff extends Person {
    private String location;
    private String reviews;
    private List<Order> orders = new ArrayList<Order>();
    public void addOrder(Order order) {
        orders.add(order);
    }
    public List<Order> getOrders()
    {
        return orders;
    }
    public DeliveryStaff(String firstName, String lastName, String email, String phoneNumber ,String location , String reviews)
    {
        super(firstName,lastName,email,phoneNumber);
        this.location=location;
        this.reviews=reviews;
    }
    public DeliveryStaff(String[] parts)
    {
        super(parts[0],parts[1],parts[2],parts[3]);
        this.location=parts[4];
        this.reviews=parts[5];
    }


    public void displayInfo()
    {
        System.out.println("Hello world");
    }

    
    public void assignedOrders(List<Order> orders) {
    for (Order order : orders) {
        System.out.println("Processing order: " + order.getOrderIDColumn());
        System.out.println("Order state: " + order.getOrderStatusColumn());
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
    {   if(order.getOrderIDColumn()==orderId) {
        Integer orderState = order.getOrderStatusColumn();
        if (orderState != null && orderState == 1) { // 1:on progress
            System.out.println("in progress");
            order.setOrderStatusColumn(2); // 2:on his way
            System.out.println("on his way");
        } else if (orderState == 2) {
            order.setOrderStatusColumn(3);//3:delivered
            System.out.println("delivered");
        }
    }
    }
}

public void Reviews(List<Order>orders)
{   for(Order order: orders)
{
    Integer orderState = order.getOrderStatusColumn();
    if(orderState==3)
    {
        System.out.println("customer review delivery staff");
    }
}

}

}
