package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;



public class addRestaurant {






    public class AddRestaurantController {

        @FXML
        private TextField restaurantNameField;  //da al variable al haymsk al name y hassaaaaaaan

        public void addRestaurant() {
            String restaurantName = restaurantNameField.getText();
            if (restaurantName.isEmpty()) {
                showAlert("Error", "Restaurant name cannot be empty.");
                return;
            }

            showAlert("Success", "Restaurant added successfully.");
            restaurantNameField.clear();
        }

        private void showAlert(String title, String message) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
    }

}
