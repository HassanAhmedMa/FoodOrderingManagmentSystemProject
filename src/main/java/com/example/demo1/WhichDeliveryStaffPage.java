package com.example.demo1;

import Personchild.DeliveryStaff;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WhichDeliveryStaffPage implements Initializable {
    public ImageView backToLogin;
    @FXML
    private Button EnterDeliveryStaff;
    @FXML
    private ComboBox<String> deliveryStaff;

    private Parent root;
    private Scene scene;
    private Stage stage;
    public void switchToLogin(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setFullScreen(false); HelloApplication.centerStage(stage);
        stage.setFullScreenExitHint(""); // Suppress the default ESC message
        stage.show();

    }

    public void switchToDeliveryStaffPage(ActionEvent event) throws IOException {
        deliveryStaffController.deliveryStaffName = deliveryStaff.getSelectionModel().getSelectedItem();
        root = FXMLLoader.load(getClass().getResource("deliveryStaffPage.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setFullScreen(false); HelloApplication.centerStage(stage);
        stage.setFullScreenExitHint(""); // Suppress the default ESC message
        stage.show();
        stage.setFullScreenExitHint(""); // Suppress the default ESC message
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(DeliveryStaff staff : Files.deliveryStaffList)
        {
            deliveryStaff.getItems().addAll(staff.getFirstName());
        }


    }
}
