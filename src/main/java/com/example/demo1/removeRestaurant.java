package com.example.demo1;

import Entities.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import java.util.List;
public class removeRestaurant {


    private Parent root;
    private Scene scene;
    private Stage stage;




    @FXML
    private TextField restaurantNameField;

    public void removeRestaurant() {
        String restaurantName = restaurantNameField.getText();
        if (restaurantName.isEmpty()) {
            showAlert("Error", "Restaurant name cannot be empty.");
       } else {
            if(Files.returnByName(restaurantName) != null) {
                Restaurant restaurant = Files.returnByName(restaurantName);
                Files.restaurants.remove(restaurant);
            }
            else
            {
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
