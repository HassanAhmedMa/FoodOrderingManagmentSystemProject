package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class removeRestaurant {

    @FXML
    private TextField restaurantNameField;

    public void removeRestaurant() {
        String restaurantName = restaurantNameField.getText();
        if (restaurantName.isEmpty()) {
            showAlert("Error", "Restaurant name cannot be empty.");
//        } else if (AdminPage.restaurantList.remove(restaurantName)) {
            showAlert("Success", "Restaurant removed successfully.");
            restaurantNameField.clear();
        } else {
            showAlert("Error", "Restaurant not found.");
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
