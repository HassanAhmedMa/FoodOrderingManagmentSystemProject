package com.example.demo1;

import Entities.FoodItem;
import Entities.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EditRestaurant implements Initializable {
    public Button removeCategoryButton;
    public Button updateCategoryButton;
    public Button addCategoryButton;
    public TextField categoryTextField;
    @FXML
    private TextField RestaurantNameTextField;

    @FXML
    private TextField areasTextField;

    @FXML
    private ImageView backToAdminButton;

    @FXML
    private ImageView restaurantImage;

    @FXML
    private TextField governorateTextField;

    @FXML
    private ComboBox<String> restaurantCategoriesComboBox;

    @FXML
    private ComboBox<String> restaurantsComboBox;

    @FXML
    private Button submitRestaurantChanges;
    @FXML
    private TextField Price;

    @FXML
    private TextField Type;
    @FXML
    private ComboBox<String> menu;
    @FXML
    private TextField itemName;


    private void setRestaurantTextPrompt(Restaurant restaurant)
    {

        RestaurantNameTextField.setPromptText(restaurant.getName());
        areasTextField.setPromptText(restaurant.getArea().toString());
        governorateTextField.setPromptText(restaurant.getGovernorate().toString());

    }
    private void setFooditemTextPrompt(FoodItem FoodItem)
    {
        Price.setPromptText(String.valueOf(FoodItem.getPrice()));
        Type.setPromptText(FoodItem.getType());
        itemName.setPromptText(FoodItem.getName());


    }
    private Restaurant restaurant;
    private FoodItem selectedfoodItem;
    public void onSelection(ActionEvent actionEvent)
    {
        restaurant = Files.returnByName(restaurantsComboBox.getSelectionModel().getSelectedItem());
        restaurantCategoriesComboBox.getItems().clear();
        String selectetRestaurant = restaurantsComboBox.getSelectionModel().getSelectedItem();
        System.out.println(selectetRestaurant);
        setRestaurantTextPrompt(Files.returnByName(selectetRestaurant));
        for(String category : Files.returnByName(selectetRestaurant).getCategories())
        {
            restaurantCategoriesComboBox.getItems().add(category);
        }
        for(FoodItem foodItem : restaurant.getMenuItems()){
            menu.getItems().add(foodItem.getName());

        }
        Image image = new Image(restaurant.getImgLocation());
        restaurantImage.setImage(image);

    }
    public void onSelectionFoodItem (ActionEvent actionEvent) {
        String selectedFoodName = menu.getSelectionModel().getSelectedItem();
        if (selectedFoodName != null) {

            for (FoodItem foodItem : restaurant.getMenuItems()) {
                if (foodItem.getName().equals(selectedFoodName)) {
                    selectedfoodItem = foodItem;
                    setFooditemTextPrompt(selectedfoodItem);
                    break;
                }}}
    }

    public void updateCategory(ActionEvent actionEvent)
    {
        restaurant.editCategory(restaurantCategoriesComboBox.getSelectionModel().getSelectedItem(),categoryTextField.getText());
        restaurantCategoriesComboBox.getItems().clear();
        for(String category : restaurant.getCategories())
        {
            restaurantCategoriesComboBox.getItems().add(category);
        }
        categoryTextField.clear();
    }
    public void addCategory(ActionEvent actionEvent)
    {
        restaurant.addCategory(categoryTextField.getText());
        restaurantCategoriesComboBox.getItems().add(categoryTextField.getText());
        categoryTextField.clear();

    }
    public void removeCategory(ActionEvent actionEvent)
    {

        restaurant.removeCategory(restaurantCategoriesComboBox.getSelectionModel().getSelectedItem());
        restaurantCategoriesComboBox.getItems().remove(restaurantCategoriesComboBox.getSelectionModel().getSelectedItem());
    }
    public void updateFoodItemName(ActionEvent actionEvent) {
        if (selectedfoodItem == null) {
            showAlert("Error", "Please select a food item to update.");
            return;
        }

        String newName = itemName.getText().trim();

        if (newName.isEmpty()) {
            showAlert("Error", "Name cannot be empty.");
            return;
        }

        // Update the name
        selectedfoodItem.setName(newName);

        // Refresh the menu ComboBox
        refreshMenuItems();

        showAlert("Success", "Food item name updated successfully!");

        // Clear the input field
        itemName.clear();
    }
    public void updateFoodItemType(ActionEvent actionEvent) {
        if (selectedfoodItem == null) {
            showAlert("Error", "Please select a food item to update.");
            return;
        }

        String newType = Type.getText().trim();

        if (newType.isEmpty()) {
            showAlert("Error", "Type cannot be empty.");
            return;
        }

        // Update the type
        selectedfoodItem.setType(newType);

        showAlert("Success", "Food item type updated successfully!");

        // Clear the input field
        Type.clear();
    }
    public void updateFoodItemPrice(ActionEvent actionEvent) {
        if (selectedfoodItem == null) {
            showAlert("Error", "Please select a food item to update.");
            return;
        }

        String priceText = Price.getText().trim();

        if (priceText.isEmpty()) {
            showAlert("Error", "Price cannot be empty.");
            return;
        }

        float newPrice;
        try {
            newPrice = Float.parseFloat(priceText); // Parse price to float
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid price.");
            return;
        }

        // Update the price
        selectedfoodItem.setPrice(newPrice);

        showAlert("Success", "Food item price updated successfully!");

        // Clear the input field
        Price.clear();

    }

    private void refreshMenuItems() {
        menu.getItems().clear();
        for (FoodItem foodItem : restaurant.getMenuItems()) {
            menu.getItems().add(foodItem.getName());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        refreshRestaurants();



    }

    public void submitRestaurantChanged()
    {
        if(!RestaurantNameTextField.getText().isEmpty())
        {
            if(!RestaurantNameTextField.getText().contains(" "))
            {
                restaurant.setName(RestaurantNameTextField.getText());
            }
            else
            {
                showAlert("Error", "Error in name field cannot contain spaces");
            }
        }

        if(!areasTextField.getText().isEmpty())
        {
            if(!areasTextField.getText().contains(" "))
            {
                restaurant.setArea(List.of(areasTextField.getText()));
            }
            else{
                restaurant.setArea(List.of(areasTextField.getText().split("\\s+")));
                System.out.println(restaurant.getArea());
            }
        }
        if(!governorateTextField.getText().isEmpty())
        {
            if(!governorateTextField.getText().contains(" "))
            {
                restaurant.setGovernorate(List.of(governorateTextField.getText()));
            }
            else{
                restaurant.setGovernorate(List.of(governorateTextField.getText().split("\\s+")));
                System.out.println(restaurant.getGovernorate());
            }
        }

        governorateTextField.clear();
        areasTextField.clear();
        RestaurantNameTextField.clear();
        setRestaurantTextPrompt(restaurant);
        restaurant.setImgLocation(restaurantImage.getImage().getUrl());




    }

    private void refreshRestaurants()
    {
        restaurantsComboBox.getItems().clear();
        restaurantsComboBox.getItems().addAll(Files.RestaurantnamesList);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public void submitFoodItemChanges(ActionEvent actionEvent)
    {
        String tempString = "";
//        updateFoodItemPrice(actionEvent);
//        updateFoodItemType(actionEvent);
//        updateFoodItemName(actionEvent);

        if(selectedfoodItem != null)
        {

            if(!Type.getText().isEmpty()){
                tempString = Type.getText().trim();
                selectedfoodItem.setType(tempString);
            }

            if(!itemName.getText().isEmpty())
            {
                tempString = itemName.getText().trim();
                selectedfoodItem.setName(tempString);
            }
            if(!Price.getText().isEmpty())
            {
                Float newPrice;
                tempString = Price.getText().trim();
                try{
                    newPrice = Float.parseFloat(tempString);
                }
                catch(NumberFormatException e)
                {
                    showAlert("Error", "Price is not a valid number.");
                    newPrice = selectedfoodItem.getPrice();
                }



                selectedfoodItem.setPrice(newPrice);
            }



        }
        itemName.clear();
        Price.clear();
        Type.clear();
        setFooditemTextPrompt(selectedfoodItem);

    }




    public void setRestaurantImage(javafx.scene.input.MouseEvent mouseEvent) {
        // Create a FileChooser instance
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a Photo");

        // Set the initial directory to the resources folder in your project
        File resourcesFolder = new File("src/main/resources"); // Adjust the path if necessary
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
        File selectedFile = fileChooser.showOpenDialog(restaurantImage.getScene().getWindow());

        if (selectedFile != null) {
            // Display the selected image in the ImageView
            Image image = new Image(selectedFile.toURI().toString());
            restaurantImage.setImage(image);
        }

    }

    Parent root;
    Stage stage;
    Scene scene;


    public void returnToAdminPage(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AdminPage.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setFullScreen(false); HelloApplication.centerStage(stage);
        stage.setFullScreenExitHint(""); // Suppress the default ESC message
        stage.show();
    }
}
