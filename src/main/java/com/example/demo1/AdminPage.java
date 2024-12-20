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
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import java.util.List;


public class AdminPage {
    private Parent root;
    private Scene scene;
    private Stage stage;

    private List<String> restaurantList = new ArrayList<>();

    @FXML
    private TextField restaurantNameField;








    public void switchToAddPage(MouseEvent event) throws IOException, IOException {

        root = FXMLLoader.load(getClass().getResource("addRestaurant.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setFullScreen(false); HelloApplication.centerStage(stage);
        HelloApplication.centerStage(stage);
        stage.show();
        stage.setFullScreenExitHint(""); // Suppress the default ESC message


    }


    public void switchToRemovePage(MouseEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("removeRestaurant.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setFullScreen(false); HelloApplication.centerStage(stage);
        stage.show();
        stage.setFullScreenExitHint(""); // Suppress the default ESC message
    }


    public void switchToUpdatePage(MouseEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("EditRestaurant.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setFullScreen(false); HelloApplication.centerStage(stage);
        stage.setFullScreenExitHint(""); // Suppress the default ESC message
        stage.show();

    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void SwitchtoLogin(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setFullScreen(false); HelloApplication.centerStage(stage);
        stage.setFullScreenExitHint(""); // Suppress the default ESC message
        stage.show();

    }

}
