package com.example.demo1;

import Entities.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import java.util.List;
public class removeRestaurant {


    private Parent root;
    private Scene scene;
    private Stage stage;






    @FXML
    private ComboBox<String> RevmoveRestaurantName;
    @FXML
    private Button removeButton;
    @FXML
    public void initialize() {
        // Populate the ComboBox with restaurant names
        for (Restaurant restaurant : Files.restaurants) {
            RevmoveRestaurantName.getItems().add(restaurant.getName());
        }
    }

    public void removeRestaurant(ActionEvent event) {
        String selectedRestaurantName = RevmoveRestaurantName.getValue(); // Get the selected name
        if (selectedRestaurantName == null || selectedRestaurantName.isEmpty()) {
            showAlert("Error", "Please select a restaurant to remove.");
        } else {
            Restaurant restaurant = Files.returnByName(selectedRestaurantName); // Find restaurant by name
            if (restaurant != null) {
                Files.restaurants.remove(restaurant); // Remove the restaurant from the list
                RevmoveRestaurantName.getItems().remove(selectedRestaurantName); // Update the ComboBox
                showAlert("Success", "Restaurant and its items removed successfully.");
            } else {
                showAlert("Error", "Restaurant does not exist.");
            }
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void backToAdminPage(MouseEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("AdminPage.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        HelloApplication.centerStage(stage);
        stage.setFullScreen(false); HelloApplication.centerStage(stage);
        stage.setFullScreenExitHint(""); // Suppress the default ESC message
        stage.show();
    }
}
