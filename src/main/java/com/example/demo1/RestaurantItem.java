package com.example.demo1;

import Entities.Restaurant;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RestaurantItem implements Initializable {
    public Label Area;
    @FXML
    private Label category;

    @FXML
    private Label rating;

    @FXML
    private Label restaurantName;

    @FXML
    private ImageView restrauntImage;

    private Restaurant restaurant;

    public void myFunction() {
        System.out.println("Zobry Manga");
    }

    public void setData(Restaurant takenRestaurant) {


        restaurant = new Restaurant(takenRestaurant.getName(), takenRestaurant.getGovernorate(), takenRestaurant.getArea(), takenRestaurant.getCategories());
        restaurant.setImgLocation(takenRestaurant.getImgLocation());
        String concatinated = "";

        restaurantName.setText(this.restaurant.getName());
        Image image = new Image(restaurant.getImgLocation());

      // if(takenRestaurant.getRating() > 4)
      // {
      //     concatinated = "Amazing";
      // }
      // else if(takenRestaurant.getRating() > 3)
      // {
      //     concatinated = "Very Good";
      // }
      // else if(takenRestaurant.getRating() > 2)
      // {
      //     concatinated = "Good";
      // }
      // else if(takenRestaurant.getRating() > 1)
      // {
      //     concatinated = "Poor";
      // }
        category.setText(takenRestaurant.getCategories().toString().substring(1,takenRestaurant.getCategories().toString().length()-1));
        Area.setText(takenRestaurant.getArea().toString().substring(1,takenRestaurant.getArea().toString().length()-1));
       // rating.setText(concatinated);


        restrauntImage.setImage(image);


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
