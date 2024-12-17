package com.example.demo1;

import Entities.FoodItem;
import Entities.Restaurant;
import com.fasterxml.jackson.core.json.DupDetector;
import javafx.event.ActionEvent;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class selectedrestrauntpageController implements Initializable {

    public ImageView cart;
    @FXML
    private ImageView backGround_id;

    @FXML
    private StackPane firstScrollPane_id;

    @FXML
    private GridPane grid_id;

    @FXML
    private ScrollPane scroll_id;

    @FXML
    private StackPane secondStackPane_id;

    @FXML
    private ImageView square_id;

    //private List<FoodItem> foods=new ArrayList<>();

    private List<FoodItem> getData(){
        List<FoodItem> foods=new ArrayList<>();
        FoodItem food;

        for(int i=0; i<10;i++){
            food=new FoodItem("Potato wedges",10.00F,"", "NoImageAvailable.png");


            foods.add(food);
        }
        return foods;
    }

    public void goToCart(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CartPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.setFullScreenExitHint(""); // Suppress the default ESC message

        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        int column = 0;
        int row = 0;
        int i = 0;
        Restaurant restaurant = Files.returnByName(RestaurantItem.getSelectedRestaurantName());
        List<FoodItem> ListOfFoodItems = restaurant.getMenuItems();

        try {
            for ( FoodItem food : ListOfFoodItems )
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Fooditem.fxml"));
                AnchorPane pane = (AnchorPane) loader.load();


                FooditemController controller = loader.getController();

                controller.setData(food);



                grid_id.add(pane, column, row++);
                GridPane.setMargin(pane, new Insets(10,10,10,10));




            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private Parent root;
    private Scene scene;
    private Stage stage;
    public void ReturnToResturant(MouseEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("restrauntsPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.setFullScreenExitHint(""); // Suppress the default ESC message
        stage.show();
        //System.out.println("SelectedRestaurant Is Made Successfully");
    }

}


