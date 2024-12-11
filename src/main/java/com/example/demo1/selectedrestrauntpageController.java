package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class selectedrestrauntpageController implements Initializable {

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

    private List<Food> foods=new ArrayList<>();
    private List<Food> getData(){
        List<Food> foods=new ArrayList<>();
        Food food;

        for(int i=0; i<10;i++){
            food=new Food("Potato wedges",10.00F,"");

            //food.setImgsrc("/img/item.png");
            foods.add(food);
        }
        return foods;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int column = 0;
        int row = 0;
        int i = 0;
        for (Food food : getData())
        {
            foods.add(food);


        }

        try {
            for ( Food food : foods )
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Fooditem.fxml"));
                AnchorPane pane = (AnchorPane) loader.load();


                FooditemController controller = loader.getController();

                controller.setData(foods.get(i++));



                grid_id.add(pane, column, row++);
                GridPane.setMargin(pane, new Insets(10,10,10,10));




            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

