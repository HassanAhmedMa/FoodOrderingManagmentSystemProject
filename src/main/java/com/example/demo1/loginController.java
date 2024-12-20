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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class loginController {

    public Button SignUpButton;
    public Label noUserNameAlert;
    public Label  UserNameNotFoundAlert;
    public Label IncorrectPasswordAlert;

    private Parent root;
    private Scene scene;
    private Stage stage;


    public TextField User;
    public TextField passwordTextField;
    public Button loginButton;
    public ImageView adminLogin;
    public Button signUpButton;


    public boolean isValidAdminEmail(String email) {
        // Regular expression to match emails ending with @admin.com
        String adminEmailRegex = "^[\\w.-]+@admin\\.com$";

        // Return true if the email matches the regex, false otherwise
        return email.matches(adminEmailRegex);
    }

    public void initialize() {

        User.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        passwordTextField.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");








        System.out.println("centre Image Function is working !");
    }


    public void switchToMainPage(ActionEvent event) throws IOException {
        if(String.valueOf(User.getText().toString()).isEmpty())
        {
            IncorrectPasswordAlert.setVisible(false);
            noUserNameAlert.setVisible(true);
            UserNameNotFoundAlert.setVisible(false);
        }
        else if(!SignupController.users.containsKey(String.valueOf(User.getText().toString())))
        {
            System.out.println("User is not found");
            UserNameNotFoundAlert.setVisible(true);
            IncorrectPasswordAlert.setVisible(false);
            noUserNameAlert.setVisible(false);
        }
        else if(!passwordTextField.getText().equals(SignupController.users.get(User.getText())))
        {
            IncorrectPasswordAlert.setVisible(true);
            noUserNameAlert.setVisible(false);
            UserNameNotFoundAlert.setVisible(false);
        }

        else if (passwordTextField.getText().equals(SignupController.users.get(User.getText())))
        {
            HelloApplication.LoggedInUserName = User.getText();
            IncorrectPasswordAlert.setVisible(false);
            noUserNameAlert.setVisible(false);
            root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setFullScreen(false); HelloApplication.centerStage(stage);
            stage.setFullScreenExitHint(""); // Suppress the default ESC message
            HelloApplication.centerStage(stage);
            stage.show();

        }




    }


    public void switchToDeliveryStaff(MouseEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("whichDeliveryStaffPage.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setFullScreen(false); HelloApplication.centerStage(stage);
        HelloApplication.centerStage(stage);
        stage.setFullScreenExitHint(""); // Suppress the default ESC message
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
        stage.setFullScreen(false); HelloApplication.centerStage(stage);
        stage.setFullScreenExitHint(""); // Suppress the default ESC message
        stage.show();
    }



    public void SwitchToAdminPage(MouseEvent event) throws IOException {
        String email = User.getText(); // Get the email from the User TextField

        if (isValidAdminEmail(email)) {
            // Proceed to the AdminPage if the email is valid
            root = FXMLLoader.load(getClass().getResource("AdminPage.fxml"));
            scene = new Scene(root);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setFullScreen(false);
            HelloApplication.centerStage(stage);
            stage.setFullScreenExitHint(""); // Suppress the default ESC message
            stage.show();
        } else {
            // Display an error message if the email is invalid
            System.out.println("Invalid email. Access denied.");
        }
    }





}
