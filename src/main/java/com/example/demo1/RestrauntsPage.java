package com.example.demo1;

import Entities.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class RestrauntsPage implements Initializable {
    public TextField searchByName;
    public Button searchByRestaurantButton;
    @FXML
    private GridPane grid;
    @FXML
    private ScrollPane scroll;
    private Parent root;
    private Scene scene;
    private Stage stage;
    @FXML
    private CheckBox burgercb;

    @FXML
    private CheckBox chickencb;
    @FXML
    private CheckBox meatcb;

    @FXML
    private CheckBox pizzacb;

    @FXML
    private CheckBox saladcb;
    @FXML
    private CheckBox shawermacb;
    @FXML
    private VBox Vbox;
    private String resourcePath = "C:/Users/PC/IdeaProjects/Project/src/main/resources/ImageResources";
    public void SwitchToHomePage(MouseEvent event) throws IOException {


        root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(false); HelloApplication.centerStage(stage);
        stage.setFullScreenExitHint(""); // Suppress the default ESC message
        HelloApplication.centerStage(stage);
        HomePage.isGoingToShowAll = false;
        stage.show();

    }

    List<CheckBox> checkBoxList = new ArrayList<>();
    public void checkBoxHandler(List<Restaurant> restaurant)
    {
        boolean ifAtLeastOneIsSelected = false;
        List<Restaurant> listOfRestaurantsWithDuplicates = new ArrayList<>();
        for(CheckBox checkBox :checkBoxList )
        {

            if(checkBox.isSelected())
            {
                ifAtLeastOneIsSelected = true;
                System.out.println(checkBox.getText());
                if(HomePage.isGoingToShowAll)
                {
                    listOfRestaurantsWithDuplicates.addAll(Files.returnRestaurantByType(checkBox.getText()));
                    System.out.println(listOfRestaurantsWithDuplicates);
                }else
                {
                    for(Restaurant restaurant1 : restaurant)
                    {
                        if(Restaurant.isRestaurantOfType(restaurant1, checkBox.getText()))
                        {
                            listOfRestaurantsWithDuplicates.add(restaurant1);
                        }
                    }
                }


            }
        }
        if(ifAtLeastOneIsSelected == false)
        {
            listOfRestraunts = Files.restaurants;
        }else
        {
            listOfRestraunts = listOfRestaurantsWithDuplicates.stream().distinct().collect(Collectors.toList());
        }





    }

    public void filterButton(ActionEvent event) {
        isGoingToWorkWithTypeFilter = true;
        grid.getChildren().clear();
        setGridChildren();

    }

    private boolean isGoingToWorkWithNameFilter = false;
    private boolean isGoingToWorkWithTypeFilter = false;

    public List<Restaurant> searchByName(List<Restaurant> restaurantsList ,String name){
        System.out.println(restaurantsList);
        List<Restaurant> SearchRestaurant = new ArrayList<>();
        List<Restaurant> restaurantsToSearchFrom = restaurantsList;
        boolean restaurantIsFound = false;
        for (Restaurant restaurant : restaurantsToSearchFrom) {
            System.out.println(name + " " + restaurant.getName());
            if (restaurant.getName().equalsIgnoreCase(name)) {
                SearchRestaurant.add(restaurant);
                restaurantIsFound = true;
            }
        }
        if (!restaurantIsFound) {
            System.out.println("Restaurant not found");
        }

        return SearchRestaurant;
    }


    List<String> restrauntNames = new ArrayList<>();


    private List<Restaurant> listOfRestraunts = new ArrayList<Restaurant>();


    public void initialize(URL location, ResourceBundle resources) {
        setGridChildren();
        for(Node node : Vbox.getChildren())
        {
            if(node instanceof CheckBox)
            {
                checkBoxList.add((CheckBox) node);
            }
        }
    }

    public void setGridChildren()
    {
        listOfRestraunts = new ArrayList<>();
        if(HomePage.isGoingToShowAll)
        {

            if(!isGoingToWorkWithNameFilter)
            {
                listOfRestraunts = Files.restaurants;
            }
            else
            {
                System.out.println(Files.restaurants);
                listOfRestraunts = searchByName(Files.restaurants  ,searchByName.getText());
                searchByName.clear();
            }
            if(isGoingToWorkWithTypeFilter)
            {
                listOfRestraunts = new ArrayList<>();
                checkBoxHandler(Files.restaurants);
                isGoingToWorkWithTypeFilter = false;
            }


        }else {
            if(!isGoingToWorkWithNameFilter)
            {
                listOfRestraunts = HomePage.matchingRestaurants;
            }
            else
            {
                listOfRestraunts = searchByName(HomePage.matchingRestaurants  ,searchByName.getText());
                searchByName.clear();
            }
            if(isGoingToWorkWithTypeFilter)
            {
                listOfRestraunts = new ArrayList<>();
                checkBoxHandler(HomePage.matchingRestaurants);
                isGoingToWorkWithTypeFilter = false;
            }
        }





        int column = 0;
        int row = 0;

//        grid.getChildren().clear(); // Clear previous children

        try {
            for (Restaurant restaurant : listOfRestraunts) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("restaurantItem.fxml"));
                AnchorPane pane = loader.load();

                RestaurantItem controller = loader.getController();
                controller.setData(restaurant);

                grid.add(pane, column, row++);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Log error instead of halting
        }
    }



    public void Search(ActionEvent actionEvent) {
        if(searchByName.getText() != null && !searchByName.getText().equals(""))
        {
            grid.getChildren().clear();
            isGoingToWorkWithNameFilter = true;

        }
        else if(searchByName.getText().equals(""))
        {
            isGoingToWorkWithNameFilter = false;
        }

        setGridChildren();
    }
}