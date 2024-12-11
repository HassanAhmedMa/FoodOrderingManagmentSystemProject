package com.example.demo1;

import Entities.Restaurant;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RestrauntsPage implements Initializable {
    public TextField searchByName;
    public Button searchByRestaurantButton;
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


    public void Search()
    {

    }


    List<String> restrauntNames = new ArrayList<>();


    private List<Restaurant> listOfRestraunts = new ArrayList<Restaurant>();

    private List<Restaurant> getData() throws FileNotFoundException {
        System.out.println(Files.RestaurantnamesList);
        Restaurant restaurant;
        List<Restaurant> restaurants = new ArrayList<>();
        int i = 0;
        for(String name : Files.RestaurantnamesList)
        {

            restaurant = new Restaurant(name,Files.listOfGovernorate.get(i),Files.listOfAreas.get(i), Files.CategoriesList.get(i));
            restaurant.setImgLocation(Files.listOfImagesPath.get(i));
            restaurants.add(restaurant);
            i++;

        }




        return restaurants;
    }

    public void initialize(URL location, ResourceBundle resources) {

        int column = 0;
        int row = 0;
        int i = 0;
        try {
            for (Restaurant restaurant : getData())
            {
                listOfRestraunts.add(restaurant);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
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
