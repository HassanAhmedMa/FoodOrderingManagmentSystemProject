package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @FXML


    public static void main(String[] args) {
        launch();
    }

    public static String LoggedInUserName="";
    @Override
    public void start(Stage stage) throws IOException {

        try{
            Files.loadAll("src/main/resources/RestaurantNames.txt","src/main/resources/RestaurantCategories.txt","src/main/resources/Governorate.txt","src/main/resources/Areas.txt","src/main/resources/RestaurantsImages.txt");
            //Files.printALlData();
        }catch (IOException e){
            e.printStackTrace();

        }
        //Files.setRestaurantNamesList();
        Files.setFoodItems("src/main/resources/FoodItems.txt");


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);


        scene.setOnKeyPressed(event -> {
            if (Objects.requireNonNull(event.getCode()) == KeyCode.F11) {
                stage.setFullScreen(!stage.isFullScreen());
            }
        });
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setFullScreen(false);




        stage.resizableProperty().setValue(Boolean.FALSE);

        stage.show();


    }


}