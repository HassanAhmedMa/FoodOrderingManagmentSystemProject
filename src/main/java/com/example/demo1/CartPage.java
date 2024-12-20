package com.example.demo1;

import Entities.FoodItem;
import Entities.Order;
import Entities.Restaurant;
import Personchild.Customer;
import Personchild.DeliveryStaff;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class CartPage implements Initializable {
    @FXML
    public GridPane grid;

    @FXML
    public ScrollPane scroll;
    public Label TotalPrice;
    public Button OrderNow;
    private int column =0;
    private int row =0;
    public static List<FoodItem> foodItems = new ArrayList<FoodItem>();
   // public FoodItem foodItem = new FoodItem("demo",123f,"demoType","McDonalds-logo.png");





    Customer customer = Files.returnCustomerByName(HelloApplication.LoggedInUserName);
    public void updateTotalPrice()
    {

        System.out.println("Trying to update total price");
        TotalPrice.setText("Total Price : " + String.format("%.2f" , customer.calculateTotal()));



    }

    @FXML
    public void OrderNowAction(ActionEvent event) throws IOException {
        Order order = new Order(HelloApplication.orderID++,1,TempCustomer.getCart(),TempCustomer.getUsername());
        order.setOrderPrice(customer.calculateTotal());
        order.setOrderLocation(TempCustomer.getGovernorate() + ", " +   TempCustomer.getArea());
        Customer customer = Files.returnCustomerByName(HelloApplication.LoggedInUserName);
        customer.addToOrderHistory(order);
        boolean available = false;
        customer.setCart(new ArrayList<>());




        for(DeliveryStaff staff: Files.deliveryStaffList)
        {
            System.out.println(TempCustomer.getGovernorate());
            System.out.println(TempCustomer.getArea());
            System.out.println(staff.getAreas());
            System.out.println(staff.getLocation());
            if(TempCustomer.getGovernorate().equalsIgnoreCase(staff.getLocation()))
            {
                for(String area :staff.getAreas())
                {
                    if(area.equalsIgnoreCase(TempCustomer.getArea()))
                    {
                        order.setDeliveryingStaff("Your hero for today is " + staff.getFirstName());
                        staff.addOrder(order);
                        available = true;
                        break;
                    }

                }

            }

        }


        if(available)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION); // You can use other types: CONFIRMATION, WARNING, ERROR
            alert.setTitle("Order Alert");
            alert.setHeaderText("Order is in progress");
            alert.setContentText("Go check your orders !");
            alert.showAndWait();
            root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            HelloApplication.centerStage(stage);
            stage.setFullScreen(false); HelloApplication.centerStage(stage);
            stage.show();
            stage.setFullScreenExitHint(""); // Suppress the default ESC message
            //Show the alert
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING); // You can use other types: CONFIRMATION, WARNING, ERROR
            alert.setTitle("Order Alert");
            alert.setHeaderText("NO ORDERS AVAILABLE FOR NOW");
            alert.setContentText("no available derivers in your area for now");
            alert.showAndWait();
            root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            HelloApplication.centerStage(stage);
            stage.setFullScreen(false); HelloApplication.centerStage(stage);
            stage.show();
            stage.setFullScreenExitHint(""); // Suppress the default ESC message
        }




    }


    public void removeItemFromGrid(FoodItem foodItem)
    {
        for (Node node : grid.getChildren()) {
            if (node instanceof Pane) {
                Pane pane = (Pane) node;

                // Check if this pane's controller matches the foodItem
                CartFoodItem controller = (CartFoodItem) pane.getUserData(); // Assuming you set the controller as user data
                if (controller != null && controller.foodItem.getName().equalsIgnoreCase(foodItem.getName())) {
                    grid.getChildren().remove(pane);
                    break;
                }
            }
        }
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
            HelloApplication.centerStage(stage);
            stage.setFullScreen(false); HelloApplication.centerStage(stage);
            stage.setFullScreenExitHint(""); // Suppress the default ESC message
            stage.show();


    }
    Customer TempCustomer = Files.returnCustomerByName(HelloApplication.LoggedInUserName);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {


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
                pane.setUserData(CartFoodItemController);






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
