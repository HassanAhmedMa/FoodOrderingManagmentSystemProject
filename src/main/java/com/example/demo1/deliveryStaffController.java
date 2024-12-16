package com.example.demo1;
import Entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.awt.event.MouseEvent;
import java.util.*;

import java.net.URL;
import java.util.ResourceBundle;

public class deliveryStaffController implements Initializable {

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
    ObservableList<Order> orders = FXCollections.observableArrayList(
            Files.deliveryStaffList.get(1).getOrders()
    );
    @FXML
    private TableColumn<Order, String> locationColumn;





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
}
