package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FooditemController {
    @FXML
    private ImageView AddButton;

    @FXML
    private Label FoodName;

    @FXML
    private Label FoodPrice;

    @FXML
    private ImageView Fooditem;

    private Food food;

    public void setData( Food takenfood) {


        this.food = new Food(takenfood.getFoodName(), takenfood.getFoodPrice(), takenfood.getImgsrc());
        String concatinated = "";

        FoodName.setText(this.food.getFoodName());
        // Image image = new Image(food.get());
        FoodPrice.setText(this.food.getFoodPrice().toString());








    }



}
