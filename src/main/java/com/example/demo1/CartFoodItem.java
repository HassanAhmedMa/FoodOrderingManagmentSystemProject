package com.example.demo1;

import Entities.FoodItem;
import Personchild.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class CartFoodItem {
    @FXML
    private ImageView ItemImage;

    @FXML
    private Label ItemName;

    @FXML
    private Label Price;

    @FXML
    private ImageView minus;

    @FXML
    private ImageView plus;

    @FXML
    private Label quantity;

    @FXML
    private void incrementQt() {
        customer.IncrementFromTotal(foodItem.getName());
        foodItem.incrementQuantityInCart();

        updateQuantity();
        cartPageController.updateTotalPrice();
    }

    Customer customer = Files.returnCustomerByName(HelloApplication.LoggedInUserName);
    List<FoodItem> customerCart = customer.getCart();
    @FXML
    private void decrementQt() {

        foodItem.decrementQuantityInCart();
        customer.DecrementFromTotal(foodItem.getName());
        cartPageController.updateTotalPrice();
        if(!customerCart.contains(foodItem) && foodItem.getQuantityInCart() < 1) {
            cartPageController.removeItemFromGrid(foodItem);

        }



        updateQuantity();


    }

    CartPage cartPageController;

    FoodItem foodItem;
    public void setData(FoodItem takenfood, CartPage cartPage) {
        cartPageController = cartPage;
        System.out.println("Loading Cart Food Items");
        this.foodItem = new FoodItem(takenfood.getName(),takenfood.getPrice(),takenfood.getType(),takenfood.getImageSrc());
        foodItem.setQuantityInCart(takenfood.getQuantityInCart());

        ItemName.setText(takenfood.getName());
        Image image = new Image(takenfood.getImageSrc());
        Price.setText(((Float)takenfood.getPrice()).toString());
        ItemImage.setImage(image);
        System.out.println("Loading is Successful");
        System.out.println("TAKEN FOOD GET QUANITY IN CAR IS : " + takenfood.getQuantityInCart());
        quantity.setText("Qt: " +  ((Integer)foodItem.getQuantityInCart()).toString());



    }
    public void updateQuantity()
    {
        System.out.println("Updated Quantity on screen to : " + foodItem.getQuantityInCart());
        quantity.setText("Qt: " +  ((Integer)foodItem.getQuantityInCart()).toString());
        System.out.println(customer.getCart());
    }


}
