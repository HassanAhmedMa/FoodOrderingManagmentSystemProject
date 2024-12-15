package com.example.demo1;
import Entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.*;

import java.net.URL;
import java.util.ResourceBundle;

public class deliveryStaffController implements Initializable {

    @FXML
    private TableColumn<Order, String> CustomerNameColumn;
    @FXML
    private TableColumn<Order, Float> orderPriceColumn;

    @FXML
    private TableColumn<Order, Integer> orderStateColumn;
    @FXML
    private TableColumn<Order, Integer> orderIdColumn;

    @FXML
    private TableView<Order> table;
    ObservableList<Order> orders = FXCollections.observableArrayList(
            Files.deliveryStaffList.get(1).getOrders()
    );
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        orderIdColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("OrderIDColumn"));
        CustomerNameColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("whoOrdered"));
        orderPriceColumn.setCellValueFactory(new PropertyValueFactory<Order, Float>("orderPrice"));
        orderStateColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("OrderStatus"));

        table.setItems(orders);

    }
}
