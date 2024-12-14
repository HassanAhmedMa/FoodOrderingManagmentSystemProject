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
    public Integer qt;
    @FXML
    private void incrementQt() {
        qt++;
        customer.IncrementFromTotal(foodItem.getName());
        updateQuantity();
        cartPageController.updateTotalPrice();
    }

    Customer customer = Files.returnCustomerByName(HelloApplication.LoggedInUserName);
    List<FoodItem> customerCart = customer.getCart();
    @FXML
    private void decrementQt() {
        if(!customerCart.contains(foodItem)) {
            cartPageController.removeItemFromGrid(foodItem);
        }
        qt--;
        customer.DecrementFromTotal(foodItem.getName());
        updateQuantity();
        cartPageController.updateTotalPrice();

    }

    CartPage cartPageController;

    FoodItem foodItem;
    public void setData(FoodItem takenfood, CartPage cartPage) {
        cartPageController = cartPage;
        System.out.println("Loading Cart Food Items");
        this.foodItem = new FoodItem(takenfood.getName(), takenfood.getPrice(), takenfood.getType(), takenfood.getImageSrc());
        ItemName.setText(takenfood.getName());
        Image image = new Image(takenfood.getImageSrc());
        Price.setText(((Float)takenfood.getPrice()).toString());
        ItemImage.setImage(image);
        System.out.println("Loading is Successful");
        System.out.println("TAKEN FOOD GET QUANITY IN CAR IS : " + takenfood.getQuantityInCart());
        qt = takenfood.getQuantityInCart();
        quantity.setText(qt.toString());


    }
    public void updateQuantity()
    {
        quantity.setText(qt.toString());
    }


}
