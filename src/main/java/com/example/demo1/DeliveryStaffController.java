package com.example.demo1;

import Entities.FoodItem;
import Entities.Order;
import Personchild.Customer;
import Personchild.DeliveryStaff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.demo1.Files.getDeliveryStaff;

public class DeliveryStaffController implements Initializable {
    @FXML
    private TableColumn<Order, String> CustomerNameColumn;

    @FXML
    private TableColumn<Order, Integer> OrderIDColumn;

    @FXML
    private TableColumn<Order, Integer> OrderStatusColumn;

    @FXML
    private TableView<Order> TableOfOrders;

    @FXML
    private Button clearTable;

    @FXML
    private Button logout;

    DeliveryStaff deliveryStaff = getDeliveryStaff("joo");

    ObservableList<Order> orders = FXCollections.observableArrayList(
            new Order(1,1,List.of(new FoodItem("sushi",5,"fish","NoImageAvailable")),"mom"),
            new Order(2,1,List.of(new FoodItem("chicken",5,"chicken","NoImageAvailable")),"dad")
    );
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        OrderIDColumn.setCellValueFactory(new PropertyValueFactory<Order,Integer>("orderId"));
        OrderStatusColumn.setCellValueFactory(new PropertyValueFactory<>("orderState"));

        TableOfOrders.setItems(orders); // Ensure 'orders' is populated
    }
}
