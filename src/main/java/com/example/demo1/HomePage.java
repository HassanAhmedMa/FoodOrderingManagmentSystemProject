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
    private Button HomePageGoButton;
    @FXML
    private ImageView logout;
    @FXML
    private Label HomePageUserName;

    public void SwtichToRestraunts(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("restrauntsPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
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
        stage.show();
    }

    public void SearchbyGovernorate(ActionEvent actionEvent, TextField governorateField, ListView<String> resultsListView) throws IOException {

        String governorate = governorateField.getText().trim();
        if (governorate.isEmpty()) {
            showAlert("Error", "Please enter a governorate to search.");
            return;
        }
        List<String> matchingRestaurants = new ArrayList<>();
        for (int i = 0; i < Files.listOfGovernorate.size(); i++) {
            if (governorate.equalsIgnoreCase(Files.listOfGovernorate.get(i))) {
                matchingRestaurants.add(Files.RestaurantnamesList.get(i));
            }
        }
        root = FXMLLoader.load(getClass().getResource("restrauntsPage.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();

        if (matchingRestaurants.isEmpty()) {
            showAlert("No Results", "No restaurants found for the specified governorate.");
            resultsListView.getItems().clear(); // Clear any previous results
        } else {
            resultsListView.getItems().setAll(matchingRestaurants); // Display results in ListView
        }



    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



}

