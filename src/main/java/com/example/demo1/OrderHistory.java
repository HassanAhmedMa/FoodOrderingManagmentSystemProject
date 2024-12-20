package com.example.demo1;

import Entities.FoodItem;
import Entities.Order;
import Personchild.Customer;
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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OrderHistory implements Initializable{

    @FXML
    private TableColumn<Order, String> FoodItems;

    @FXML
    private TableColumn<Order, Integer> OrderID;

    @FXML
    private TableColumn<Order, String> OrderStatus;

    @FXML
    private TableColumn<Order, String> deliveryman;
    @FXML
    private TableColumn<Order, Float> Price;
    private Parent root;
    private Scene scene;
    private Stage stage;

    ObservableList<Order> orders = FXCollections.observableArrayList();
    @FXML
    private TableView<Order> tableOfOrderHistory;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Customer customer;
        customer = Files.returnCustomerByName(HelloApplication.LoggedInUserName);

        orders.addAll(customer.getOrderHistory());
        customer.viewOrders();




        FoodItems.setCellValueFactory(new PropertyValueFactory<Order, String>("foodItemsNameString") );
        OrderID.setCellValueFactory(new PropertyValueFactory<Order, Integer>("OrderIDColumn") );
        OrderStatus.setCellValueFactory(new PropertyValueFactory<Order, String>("orderStatusString"));
        Price.setCellValueFactory(new PropertyValueFactory<Order, Float>("orderPrice"));
        deliveryman.setCellValueFactory(new PropertyValueFactory<Order, String>("DeliveryingStaff"));
        tableOfOrderHistory.setItems(orders);


    }

    public void cancleOrder(ActionEvent event) {
         Order order =  tableOfOrderHistory.getSelectionModel().getSelectedItem();
         if(order.getOrderStatus() == 1) //if order is not still being prepared
         {
             System.out.println("MEOW");
             Customer customer = Files.returnCustomerByName(HelloApplication.LoggedInUserName);
             for(DeliveryStaff delivery : Files.deliveryStaffList)
             {
                 if(delivery.getOrders().contains(order))
                 {
                     delivery.getOrders().remove(order);
                 }
             }
             customer.cancelOrder(order);
             orders.remove(order);
         }
         else
         {
             showAlert("Cancelation Error","Order is already confirmed!");
         }

         tableOfOrderHistory.refresh();



    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }




    public void returnToHomePage(javafx.scene.input.MouseEvent event) {
        try{
            root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setFullScreen(false);
            HelloApplication.centerStage(stage);
            stage.setFullScreenExitHint(""); // Suppress the default ESC message
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
