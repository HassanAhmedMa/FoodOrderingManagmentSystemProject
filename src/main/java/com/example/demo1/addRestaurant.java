package com.example.demo1;

import Entities.Restaurant;
import com.fasterxml.jackson.core.JsonParser;
//import com.sun.javafx.tk.quantum.PaintRenderJob;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.demo1.Files.listOfImagesPath;
import static com.example.demo1.Files.restaurants;


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

    @FXML
    private ImageView SelectedrestrauntImage;
     private List<String> listOfImagesPath = new ArrayList<>(); // Store image paths

    private Restaurant restaurant; // Restaurant object to store restaurant data





    public void addRestaurant() {
        String restaurantName = NameToAddRestaurant.getText();
        String restaurantGovernorate= GovernorateToAddRestaurant.getText();
        String restaurantCategory = CategoriesToAddRestaurant.getText();
        String restaurantAreas = AreasToAddRestaurant.getText();

        if (restaurantName.isEmpty()||restaurantGovernorate.isEmpty()||restaurantCategory.isEmpty()||restaurantAreas.isEmpty()) {
            showAlert("Error", "Please fill all the fields.");
        } else {

            Files.RestaurantnamesList.add(restaurantName);
            Files.listOfGovernorate.add(restaurantGovernorate);
            Files.CategoriesList.add(new ArrayList<>(List.of(restaurantCategory)));
            Files.listOfAreas.add(new ArrayList<>(List.of(restaurantAreas)));
            listOfImagesPath.add(SelectedrestrauntImage.getImage().getUrl());
            Files.restaurants.add(new Restaurant(restaurantName,restaurantGovernorate,List.of(restaurantAreas),List.of(restaurantCategory)));
            Files.returnByName(restaurantName).setImgLocation(SelectedrestrauntImage.getImage().getUrl());


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
    @FXML
    public void Photoselection(MouseEvent event) throws IOException {
        // Create a FileChooser instance
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a Photo");

        // Set the initial directory to the resources folder in your project
        File resourcesFolder = new File("src/main/resources/photos"); // Adjust the path if necessary
        if (resourcesFolder.exists() && resourcesFolder.isDirectory()) {
            fileChooser.setInitialDirectory(resourcesFolder);
        } else {
            System.out.println("Resources folder not found!");
        }

        // Set file extension filters for image files
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        // Open the FileChooser dialog
        File selectedFile = fileChooser.showOpenDialog(SelectedrestrauntImage.getScene().getWindow());

        if (selectedFile != null) {
            // Display the selected image in the ImageView
            Image image = new Image(selectedFile.toURI().toString());
            SelectedrestrauntImage.setImage(image);


            // Add the image path to the list
            listOfImagesPath.add(selectedFile.getName());
            System.out.println("Selected image path: " + selectedFile.getName());
        }
    }
}
