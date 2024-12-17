package com.example.demo1;

import Entities.FoodItem;
import Entities.Order;
import Entities.Restaurant;
import Personchild.Customer;
import Personchild.DeliveryStaff;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;


import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {

    public static Integer orderID = 3;

    @FXML

    public static void centerStage(Stage stage) {
        // Get primary screen bounds
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        // Calculate the centered position
        double stageWidth = stage.getWidth();
        double stageHeight = stage.getHeight();

        double centerX = (screenBounds.getWidth() - stageWidth) / 2;
        double centerY = (screenBounds.getHeight() - stageHeight) / 2;

        // Set the stage position
        stage.setX(centerX);
        stage.setY(centerY);
    }

    public static void main(String[] args) {
        launch();
    }

    public static String LoggedInUserName="";
    @Override
    public void start(Stage stage) throws IOException {

        try{
            Files.loadAll("src/main/resources/RestaurantNames.txt","src/main/resources/RestaurantCategories.txt","src/main/resources/Governorate.txt","src/main/resources/Areas.txt","src/main/resources/RestaurantsImages.txt","src/main/resources/FoodItems.txt","src/main/resources/CustomerData.txt","src/main/resources/deliveryStaff.txt");
            //Files.printALlData();
        }catch (IOException e){
            e.printStackTrace();
        }



//        System.out.println(SignupController.users);
//        for(DeliveryStaff staff : Files.deliveryStaffList)
//        {
//            System.out.println(staff.getFirstName());
//            System.out.println(staff.getLocation());
//            for(Order order : staff.getOrders())
//            {
//                System.out.println("Order Price : " + order.getOrderPrice());
//                System.out.println(order.getOrderId());
//                System.out.println(order.getOrderState());
//                for(FoodItem foodItem : order.getOrderedFoodItems())
//                {
//                    System.out.println(foodItem.getName());
//                    System.out.println(foodItem.getPrice());
//                    System.out.println("Food quanitity : " + foodItem.getQuantityInCart());
//
//                }
//            }
//        }



        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        //Customer customer = Files.returnCustomerByName("ziad");
        //Restaurant restaurant = Files.restaurants.get(1);
        //customer.addFoodItemToCart(restaurant.getMenuItems().get(1));








        scene.setOnKeyPressed(event -> {
            if (Objects.requireNonNull(event.getCode()) == KeyCode.F11) {
                stage.setFullScreen(!stage.isFullScreen());
            }
        });
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setFullScreen(false);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        // Calculate the centered position
        double stageWidth = stage.getWidth();
        double stageHeight = stage.getHeight();

        double centerX = (screenBounds.getWidth() - stageWidth) / 2;
        double centerY = (screenBounds.getHeight() - stageHeight) / 2;

        // Set the stage position
        stage.setX(centerX);
        stage.setY(centerY);





        stage.resizableProperty().setValue(Boolean.FALSE);






        stage.show();


    }


}