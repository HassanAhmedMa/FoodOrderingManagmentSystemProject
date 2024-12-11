package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePage implements Initializable {
    private Parent root;
    private Scene scene;
    private Stage stage;
    @FXML
    private Button HomePageGoButton;

    @FXML
    private Label HomePageUserName;

    public void SwtichToRestraunts(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("restrauntsPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HomePageUserName.setText("Hello " + HelloApplication.LoggedInUserName);
    }
}
