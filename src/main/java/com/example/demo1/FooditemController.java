package com.example.demo1;

import Entities.FoodItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    public void setData(FoodItem takenfood) {


        this.food = new FoodItem(takenfood.getName(), takenfood.getPrice(), takenfood.getType(), takenfood.getImageSrc());


        FoodName.setText(takenfood.getName());
        Image image = new Image(takenfood.getImageSrc());
        FoodPrice.setText(((Float)takenfood.getPrice()).toString());
        FoodItemPicture.setImage(image);
        FoodType.setText(takenfood.getType());








    }



}
