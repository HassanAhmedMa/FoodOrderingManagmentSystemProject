package com.example.demo1;

import Entities.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
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
    private Parent root;
    private Scene scene;
    private Stage stage;

    @FXML
    private static String selectedRestaurantName;

    public static String getSelectedRestaurantName() {
        return selectedRestaurantName;
    }

    public static void setSelectedRestaurantName(String name) {
        selectedRestaurantName = name;
    }
    public void myFunction(MouseEvent event) throws IOException {
        selectedRestaurantName = restaurant.getName().toLowerCase();
        System.out.println(selectedRestaurantName);
        System.out.println(selectedRestaurantName);
        try{
            root = FXMLLoader.load(getClass().getResource("SelectedRestaurantPage.fxml"));
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setFullScreen(false);
            stage.setFullScreenExitHint(""); // Suppress the default ESC message
            stage.show();
            //selectedRestaurantName = restaurantName.getText();


            //System.out.println(selectedRestaurantName);

        }catch (Exception e){
            e.getStackTrace();
        }


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
