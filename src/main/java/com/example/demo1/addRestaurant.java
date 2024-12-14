package com.example.demo1;

import com.fasterxml.jackson.core.JsonParser;
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

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class addRestaurant {


    private Parent root;
    private Scene scene;
    private Stage stage;

    @FXML
    private TextField CategoriesToAddRestaurant;
    @FXML
    private TextField GovernorateToAddRestaurant;
    @FXML
    private TextField NameToAddRestaurant;
    @FXML
    private TextField AreasToAddRestaurant;


    public void addRestaurant() {
        String restaurantName = NameToAddRestaurant.getText();
        String restaurantGovernorate= GovernorateToAddRestaurant.getText();
        String restaurantCategory = CategoriesToAddRestaurant.getText();
        String restaurantAreas = AreasToAddRestaurant.getText();

        if (restaurantName.isEmpty()||restaurantGovernorate.isEmpty()) {
            showAlert("Error", "Please fill all the fields.");
        } else {

            Files.RestaurantnamesList.add(restaurantName);
            Files.listOfGovernorate.add(restaurantGovernorate);
            Files.CategoriesList.add(new ArrayList<>(List.of(restaurantCategory)));
            Files.listOfAreas.add(new ArrayList<>(List.of(restaurantAreas)));


            showAlert("Success", "Restaurant added successfully.");
            NameToAddRestaurant.clear();
            GovernorateToAddRestaurant.clear();
            CategoriesToAddRestaurant.clear();
            AreasToAddRestaurant.clear();
            System.out.println(Files.RestaurantnamesList);
            System.out.println(Files.listOfGovernorate);
            System.out.println(Files.CategoriesList);
            System.out.println(Files.listOfAreas);
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
        stage.setFullScreen(true);
        stage.show();
    }
    public void processAndAddAreas(String inputAreas) {
        List<List<String>> listOfAreas = new ArrayList<>();

        // Check if input is valid
        if (inputAreas == null || inputAreas.trim().isEmpty()) {
            showAlert("Error", "No areas entered. Please provide valid input.");
            return;
        }

        // Split input into individual areas by spaces
        String[] areas = inputAreas.trim().split("\\s+"); // Split by one or more spaces

        // Add the areas as a single group (list) to the listOfAreas
        List<String> areaList = Arrays.asList(areas);
        listOfAreas.add(areaList);

        // Add the parsed areas to the Files.listOfAreas
        Files.listOfAreas.add(areaList);

        showAlert("Success", "Areas have been successfully added.");
    }
}
