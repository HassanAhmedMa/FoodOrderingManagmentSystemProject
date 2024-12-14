package com.example.demo1;

import Entities.FoodItem;
import Entities.Restaurant;
import Personchild.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CartPage implements Initializable {
    @FXML
    public GridPane grid;

    @FXML
    public ScrollPane scroll;
    public Label TotalPrice;
    private int column =0;
    private int row =0;
    public static List<FoodItem> foodItems = new ArrayList<FoodItem>();
   // public FoodItem foodItem = new FoodItem("demo",123f,"demoType","McDonalds-logo.png");
    public void updateTotalPrice()
    {
        Customer customer = Files.returnCustomerByName(HelloApplication.LoggedInUserName);
        System.out.println("Trying to update total price");
        TotalPrice.setText("Total Price : " + customer.calculateTotal().toString());
    }

    public static boolean isFoodItemAlreadyInCart(FoodItem foodItem)
    {
        System.out.println(foodItems);
        for(FoodItem selectedFoodItem : foodItems)
        {
            if(foodItem.getName().equalsIgnoreCase(selectedFoodItem.getName()))
            {

                return true;
            }
        }
        return false;

    }
    private Parent root;
    private Stage stage;
    private Scene scene;

    public void backToRestaurants(MouseEvent mouseEvent) throws IOException {

            foodItems.removeAll(foodItems);
            root = FXMLLoader.load(getClass().getResource("restrauntsPage.fxml"));
            stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Customer TempCustomer = Files.returnCustomerByName(HelloApplication.LoggedInUserName);

            System.out.println(TempCustomer.getUsername());


            for(FoodItem foodItem : TempCustomer.getCart())
            {
                if(isFoodItemAlreadyInCart(foodItem))
                {
                    continue;
                }
                // Load the FoodItem.fxml layout
                FXMLLoader loader = new FXMLLoader(getClass().getResource("cartFoodItem.fxml"));
                // Get the controller for FoodItem.fxml
                AnchorPane pane = loader.load();
                CartFoodItem CartFoodItemController = loader.getController();

                //foodItem.setQuantityInCart(1);
                CartFoodItemController.setData(foodItem, this);







                grid.add(pane, column, row++);
                foodItems.add(foodItem);
                GridPane.setMargin(pane, new Insets(10, 0, 0, 0));



                // Add the food item pane to the grid
                updateTotalPrice();
                System.out.println("TERMINATED SUCCESSFULLY");
            }


        } catch (Exception e) {
            System.out.println("EXCEPTION IS EXECUTED!!!!");
            // Handle the exception (e.g., show an error message to the user)
        }
    }
}
