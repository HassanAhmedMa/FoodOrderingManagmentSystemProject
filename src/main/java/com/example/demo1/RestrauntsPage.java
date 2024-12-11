package com.example.demo1;

import Entities.Restaurant;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RestrauntsPage implements Initializable {
    @FXML
    private GridPane grid;
    @FXML
    private ScrollPane scroll;
    private Parent root;
    private Scene scene;
    private Stage stage;
    private String resourcePath = "C:/Users/PC/IdeaProjects/Project/src/main/resources/ImageResources";
    public void SwitchToHomePage(MouseEvent event) throws IOException {


        root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();

    }

    private List<Restaurant> listOfRestraunts = new ArrayList<Restaurant>();

    private List<Restaurant> getData()

    {
        Restaurant restaurant;
        List<Restaurant> restaurants = new ArrayList<>();
        try{
            List<String> restaurantNames = new ArrayList<String>(List.of("Mc Donald's", "Pizza hut"));
            List<String> restaurantGovernorate = new ArrayList<String>(List.of("Cairo", "Cairo"));
            List<String> restaurantArea = new ArrayList<String>(List.of("Nozha", "sheraton"));
            List<Float> restaurantRating = new ArrayList<>(List.of(4.2f,3.1f));

            List<List<String>> categoriesList = new ArrayList<>(List.of(List.of("Burger", "Salad", "Appetizers", "Soft Drinks"),List.of("Pizza", "Salad", "Appetizers")));


            List<String> imageLocationList = new ArrayList<>(List.of("McDonalds-logo.png" , "Pizza-Hut-Logo.png"));





            int i = 0;
            for(String restaurantName : restaurantNames)
            {
                restaurant = new Restaurant(restaurantNames.get(i) ,restaurantGovernorate.get(i), restaurantArea.get(i),categoriesList.get(i));
                restaurants.add(restaurant);
                restaurants.get(i).setImgLocation(imageLocationList.get(i));
                restaurants.get(i).setRating(restaurantRating.get(i));
                i++;

            }
          //  for(Restaurant restaurant1 : restaurants)
          //  {
          //      System.out.println(restaurant1.getName());
          //      System.out.println(restaurant1.getCategories());
          //  }
        }catch (Exception e){
            System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        }


        return restaurants;
    }

    public void initialize(URL location, ResourceBundle resources) {
        int column = 0;
        int row = 0;
        int i = 0;
        for (Restaurant restaurant : getData())
        {
            listOfRestraunts.add(restaurant);


        }

        try {
            for (Restaurant restaurant : listOfRestraunts)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("restaurantItem.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();


            RestaurantItem controller = loader.getController();

            controller.setData(listOfRestraunts.get(i++));



            grid.add(pane, column, row++);


        }
        } catch (IOException e) {
        throw new RuntimeException(e);
    }




    }

}
