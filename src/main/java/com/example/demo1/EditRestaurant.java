package com.example.demo1;

import Entities.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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
    private TextField governorateTextField;

    @FXML
    private ComboBox<String> restaurantCategoriesComboBox;

    @FXML
    private ComboBox<String> restaurantsComboBox;

    @FXML
    private Button submitRestaurantChanges;

    private void setRestaurantTextPrompt(Restaurant restaurant)
    {
        RestaurantNameTextField.setPromptText(restaurant.getName());
        areasTextField.setPromptText(restaurant.getArea().toString());
        governorateTextField.setPromptText(restaurant.getGovernorate().toString());

    }
    private Restaurant restaurant;
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


}
