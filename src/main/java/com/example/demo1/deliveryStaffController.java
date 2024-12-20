package com.example.demo1;
import Entities.*;
import Personchild.DeliveryStaff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.*;

import java.net.URL;
import java.util.ResourceBundle;

public class deliveryStaffController implements Initializable {
    public static String deliveryStaffName;
    public ImageView backButton;
    @FXML
    private ImageView BackOrderState;
    @FXML
    private ImageView NextOrderState;
    @FXML
    private TableColumn<Order, String> CustomerNameColumn;
    @FXML
    private TableColumn<Order, Float> orderPriceColumn;

    @FXML
    private TableColumn<Order, String> orderStateColumn;
    @FXML
    private TableColumn<Order, Integer> orderIdColumn;

    @FXML
    private TableView<Order> table;
    ObservableList<Order> orders = FXCollections.observableArrayList(Files.getDeliveryStaff(deliveryStaffName).getOrders());
    @FXML
    private TableColumn<Order, String> locationColumn;

    private Parent root;
    private Scene scene;
    private Stage stage;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        orderIdColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("OrderIDColumn"));
        CustomerNameColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("whoOrdered"));
        orderPriceColumn.setCellValueFactory(new PropertyValueFactory<Order, Float>("orderPrice"));
        orderStateColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("orderStatusString"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("orderLocation"));

        table.setItems(orders);

    }


    public void UPchangeOrderState(javafx.scene.input.MouseEvent mouseEvent) {
        if(table.getSelectionModel().getSelectedItem() != null) {
            Order order = table.getSelectionModel().getSelectedItem();
            order.UpdateOrderStatus();
            table.refresh();
        }
    }

    public void backButton(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("whichDeliveryStaffPage.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setFullScreen(false); HelloApplication.centerStage(stage);
        HelloApplication.centerStage(stage);

        stage.setFullScreenExitHint(""); // Suppress the default ESC message
        stage.show();
        stage.setFullScreenExitHint(""); // Suppress the default ESC message
    }

    public void clearDeliveredOrders(javafx.scene.input.MouseEvent mouseEvent) {
        Iterator<Order> iterator = orders.iterator();
        while (iterator.hasNext()) {
            Order order = iterator.next();
            if (order.getOrderStatus() == 3) {
                System.out.println("FOUND ORDER WITH DELIVERED STATUS");
                iterator.remove(); // Safely removes the current element
            }
        }
        table.refresh();
    }
}
