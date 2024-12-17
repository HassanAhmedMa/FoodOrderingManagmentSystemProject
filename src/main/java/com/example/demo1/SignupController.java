package com.example.demo1;

import Personchild.Customer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class SignupController {
    public Label phoneNumberError;
    public Label confirmPasswordError;
    public Label emailError;
    public Label passwordError;
    public Label usernameError;
    @FXML
    private TextField Email;

    @FXML
    private TextField Password;

    @FXML
    private TextField PhoneNumber;

    @FXML
    private Button SGINUPBUTTON;

    @FXML
    private TextField Username;

    @FXML
    private TextField ConfirmPassword;


    @FXML
    private Label test;
    private Parent root;
    private Scene scene;
    private Stage stage;

    public static HashMap<String,String> users = new HashMap<String,String>();
    public void SwitchToLogin(ActionEvent event) throws IOException {

//        if(!Password.getText().equals(ConfirmPassword.getText())){
//            myFunction();
//        }
//        else {
//
//        }

        boolean isValid = true;

        // Reset error messages visibility
        emailError.setVisible(false);
        passwordError.setVisible(false);
        confirmPasswordError.setVisible(false);
        usernameError.setVisible(false);
        phoneNumberError.setVisible(false);

        // Validate Email
        if (!Email.getText().matches("^[\\w.%+-]+@[\\w.-]+\\.com$")) {
            emailError.setText("Invalid email format. Must contain '@' and end with '.com'");
            emailError.setVisible(true);
            isValid = false;
        }

        // Validate Password
        if (!Password.getText().matches("^(?=.*[!@#$%^&*(),.?\":{}|<>])[a-zA-Z0-9!@#$%^&*(),.?\":{}|<>]{8,}$")) {
            passwordError.setText("Password must be at least 8 characters and contain a special character.");
            passwordError.setVisible(true);
            isValid = false;
        }

        // Validate Confirm Password
        if (!Password.getText().equals(ConfirmPassword.getText())) {
            confirmPasswordError.setText("Passwords do not match.");
            confirmPasswordError.setVisible(true);
            isValid = false;
        }

        // Validate Username (if needed)
        if (Username.getText().isEmpty()) {
            usernameError.setText("Username cannot be empty.");
            usernameError.setVisible(true);
            isValid = false;
        }

        // Validate Phone Number
        if (!PhoneNumber.getText().matches("^(012|010|011|015)[0-9]{8}$")) {
            phoneNumberError.setText("Phone number must start with 012, 010, 011, or 015 and be exactly 11 digits.");
            phoneNumberError.setVisible(true);
            isValid = false;
        }

        if (isValid) {
            // Proceed with user creation and scene switch
            users.put(Username.getText(), Password.getText());
            Files.listOfCustomers.add(new Customer(Username.getText(),Password.getText(),"No First Name","No Last Name",Email.getText(),PhoneNumber.getText()));
            root = FXMLLoader.load(getClass().getResource("login.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setFullScreen(false); HelloApplication.centerStage(stage);
            stage.setFullScreenExitHint(""); // Suppress the default ESC message
            stage.show();




        }

    }

    @FXML
    public void validateEmail() {
        if (!Email.getText().matches("^[\\w.%+-]+@[\\w.-]+\\.com$")) {
            emailError.setText("Invalid email format, must contain (@) and end with (.com).");
            emailError.setVisible(true);
        } else {
            emailError.setVisible(false);
        }
    }

    @FXML
    public void validatePassword() {
        if (!Password.getText().matches("^(?=.*[!@#$%^&*(),.?\":{}|<>])[a-zA-Z0-9!@#$%^&*(),.?\":{}|<>]{8,}$")) {
            passwordError.setText("Password must be at least 8 characters and contain a special character.");
            passwordError.setVisible(true);
        } else {
            passwordError.setVisible(false);
        }
    }

    @FXML
    public void validateConfirmPassword() {
        if (!ConfirmPassword.getText().equals(Password.getText())) {
            confirmPasswordError.setText("Passwords do not match.");
            confirmPasswordError.setVisible(true);
        } else {
            confirmPasswordError.setVisible(false);
        }
    }

    @FXML
    public void validatePhoneNumber() {
        if (!PhoneNumber.getText().matches("^(012|010|011|015)[0-9]{8}$")) {
            phoneNumberError.setText("Phone number must start with 012, 010, 011, or 015 and be exactly 11 digits.");
            phoneNumberError.setVisible(true);
        } else {
            phoneNumberError.setVisible(false);
        }
    }

    @FXML
    public void validateUsername() {
        if (Username.getText().isEmpty()) {
            usernameError.setText("Username cannot be empty.");
            usernameError.setVisible(true);
        }
        else if(users.containsKey(Username.getText())) {
            usernameError.setText("Username already exists.");
            usernameError.setVisible(true);
        }
        else {
            usernameError.setVisible(false);
        }
    }






    public void myFunction() {
        Timer timer = new Timer();
        TimerTask ShowPasswordsDoNotMatch = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {             //Platform Run Later makes the Javafx utilize UI threads not the background threads
                    test.setVisible(true);
                });
            }
        };
        TimerTask HidePasswordsDoNotMatch = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {             //Platform Run Later makes the Javafx utilize UI threads not the background threads
                    test.setVisible(false);
                });
            }
        };
        timer.schedule(ShowPasswordsDoNotMatch, 0); // 3-second delay
        timer.schedule(HidePasswordsDoNotMatch, 5000);
    }



    public void SwitchToLogin2(ActionEvent event) throws IOException {
        users.put(Username.getText(), Password.getText());
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(false); HelloApplication.centerStage(stage);
        stage.show();


    }
}
