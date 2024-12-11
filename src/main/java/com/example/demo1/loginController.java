package com.example.demo1;


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

import javax.swing.*;
import java.io.IOException;

public class loginController {

    public Button signUp;
    private Parent root;
    private Scene scene;
    private Stage stage;

    public TextField email;
    public TextField passwordTextField;
    public Button loginButton;
    public ImageView adminLogin;
    public Button signUpButton;

    public void initialize() {

        email.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        passwordTextField.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");








        System.out.println("centre Image Function is working !");
    }

    public void centreImage()
    {


    }

    public void switchToMainPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
       // stage.setFullScreen(true);
        stage.show();
    }

    public void myFunction()
    {
        System.out.println("Hello World");
    }

    public void SwitchToSignUp(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SignUpPage.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }



}
