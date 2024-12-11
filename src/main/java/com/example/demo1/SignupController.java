package com.example.demo1;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class SignupController {
    public Label test;
    public TextField ConfirmPassword;
    public TextField Password;
    private Parent root;
    private Scene scene;
    private Stage stage;


    public void SwitchToLogin(ActionEvent event) throws IOException {

        if(!Password.getText().equals(ConfirmPassword.getText())){
            myFunction();
        }
        else {
            root = FXMLLoader.load(getClass().getResource("login.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
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
}
