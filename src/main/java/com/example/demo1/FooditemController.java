package com.example.demo1;

import Entities.FoodItem;
import Personchild.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class FooditemController {
    public ImageView FoodItemPicture;
    public Label FoodType;
    @FXML
    private ImageView AddButton;

    @FXML
    private Label FoodName;

    @FXML
    private Label FoodPrice;

    @FXML
    private ImageView Fooditem;

    private FoodItem food;

    private static CartPage cartController; // Reference to the cart controller

    public static void setCartController(CartPage cartController) {
        System.out.println("Sat cart controller successfully");
        FooditemController.cartController = cartController;
    }


    public void addToCart(MouseEvent mouseEvent) throws IOException {
        Customer customer = Files.returnCustomerByName(HelloApplication.LoggedInUserName);
        FoodItem foodItemToIncrement;

        if(customer.checkIfFoodIsInCart(food)) {
            for(FoodItem foodItem : customer.getCart()) {
                if(foodItem.getName().equals(food.getName())) {
                    foodItemToIncrement = foodItem;
                    foodItemToIncrement.incrementQuantityInCart();
                    System.out.println("Food Item already in Cart : " + foodItem.getName() + " Incremented to " + foodItem.getQuantityInCart());
                }


            }


        }

        else {

            customer.addFoodItemToCart(food);

        }


    }

    public void setData(FoodItem takenfood) {


        this.food = new FoodItem(takenfood.getName(), takenfood.getPrice(), takenfood.getType(), takenfood.getImageSrc());


        FoodName.setText(takenfood.getName());
        Image image = new Image(takenfood.getImageSrc());
        FoodPrice.setText(((Float)takenfood.getPrice()).toString());
        FoodItemPicture.setImage(image);
        FoodType.setText(takenfood.getType());








    }



}
