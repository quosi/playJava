package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.w3c.dom.Node;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.*;
import javafx.stage.*;

import java.awt.*;
import java.util.Stack;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
/*
       // StackPane root =new StackPane();
        // adda leaf node
        GridPane grid =new GridPane();
        grid.setAlignment(Pos.CENTER);
        //root.getChildren().add(btn);
        //Scene scene =new Scene(root,800,500);

      //  Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Login");
       // primaryStage.setScene(new Scene(root, 300, 275));
        //primaryStage.setScene(scene);
        primaryStage.show();
*/

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("welcome1.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.NONE);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {

        }
    }


    public static void main(String[] args) {
        launch(args);
    }

}
