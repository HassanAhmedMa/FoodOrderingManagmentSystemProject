package com.example.demo1;

import Entities.Restaurant;
import com.almasb.fxgl.core.collection.Array;
import com.fasterxml.jackson.core.json.DupDetector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomePage implements Initializable {
    private Parent root;
    private Scene scene;
    private Stage stage;
    @FXML
    private ImageView BurgerQuickButton;

    @FXML
    private Button HomePageGoButton;

    @FXML
    private Label HomePageUserName;

    @FXML
    private ImageView SaladQuickbutton;

    @FXML
    private ImageView SandwichQuickButton;

    @FXML
    private TextField SearchGovernorate;

    @FXML
    private ImageView SushiQuickButton;

    @FXML
    private ImageView logout;

    @FXML
    private ImageView pizaaQuickButton;

    public static List<Restaurant> matchingRestaurants = new ArrayList<>();
    public static boolean isGoingToShowAll = true;





    public void SwtichToRestraunts(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("restrauntsPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint(""); // Suppress the default ESC message
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HomePageUserName.setText("Hello " + HelloApplication.LoggedInUserName);
    }


    public void logout(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("login.fxml"));

        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        HelloApplication.LoggedInUserName = "";
        stage.setFullScreenExitHint(""); // Suppress the default ESC message
        stage.show();
    }

    public void SearchbyGovernorate(ActionEvent actionEvent, TextField governorateField, ListView<String> resultsListView) throws IOException {



    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void SearchbyGovernorate(ActionEvent actionEvent) throws IOException {
        String governorate = SearchGovernorate.getText().trim();
        if (governorate.isEmpty()) {
            isGoingToShowAll=true;
            SwtichToRestraunts(actionEvent);
        }
        else
        {
            matchingRestaurants = new ArrayList<>();
            isGoingToShowAll = false;
            for (Restaurant restaurant : Files.restaurants) {
                for(String governorateName : restaurant.getGovernorate())
                {
                    if(governorateName.equalsIgnoreCase(governorate))
                    {
                        matchingRestaurants.add(restaurant);
                    }
                }
            }
            root = FXMLLoader.load(getClass().getResource("restrauntsPage.fxml"));
            stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.setFullScreenExitHint(""); // Suppress the default ESC message
            stage.show();
        }






    }

    public void quickSearch(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        ImageView Image = (ImageView) mouseEvent.getSource();
        matchingRestaurants = new ArrayList<>();
        if(Image == pizaaQuickButton)
        {
            isGoingToShowAll = false;
            for(Restaurant restaurant : Files.restaurants)
            {
                for(String category : restaurant.getCategories())
                {
                    if(category.equalsIgnoreCase("pizza"))
                    {
                        matchingRestaurants.add(restaurant);
                    }
                }

            }
        }
        else if(Image == SushiQuickButton)
        {
            isGoingToShowAll = false;
            for(Restaurant restaurant : Files.restaurants)
            {
                for(String category : restaurant.getCategories())
                {
                    if(category.equalsIgnoreCase("sushi"))
                    {
                        matchingRestaurants.add(restaurant);
                    }
                }

            }
        }
        else if(Image == SaladQuickbutton)
        {
            isGoingToShowAll = false;
            for(Restaurant restaurant : Files.restaurants)
            {
                for(String category : restaurant.getCategories())
                {
                    if(category.equalsIgnoreCase("salad"))
                    {
                        matchingRestaurants.add(restaurant);
                    }
                }

            }
        }
        else if(Image == SandwichQuickButton)
        {
            isGoingToShowAll = false;
            for(Restaurant restaurant : Files.restaurants)
            {
                for(String category : restaurant.getCategories())
                {
                    if(category.equalsIgnoreCase("sandwich"))
                    {
                        matchingRestaurants.add(restaurant);
                    }
                }

            }
        }
        else
        {
            isGoingToShowAll = false;
            for(Restaurant restaurant : Files.restaurants)
            {
                for(String category : restaurant.getCategories())
                {
                    if(category.equalsIgnoreCase("burger"))
                    {
                        matchingRestaurants.add(restaurant);
                    }
                }

            }
        }
        root = FXMLLoader.load(getClass().getResource("restrauntsPage.fxml"));
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint(""); // Suppress the default ESC message
        stage.show();
    }
}

