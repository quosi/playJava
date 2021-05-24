package sample;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;

public class BoardController {
    private GamePlayModel itsModel;

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Circle c1;
    @FXML
    private Circle c2;

    public BoardController () {
        itsModel = GamePlayModel.getInstance();
    }

    @FXML
    public void initialize()  {
        if(itsModel.GETPlayer1Color() == PlayerColor.RED){
            c1.setFill(Color.RED);
            c2.setFill(Color.YELLOW);
        }else if (itsModel.GETPlayer1Color() == PlayerColor.YELLOW) {
            c1.setFill(Color.YELLOW);
            c2.setFill(Color.RED);
        }
    }


    public void showMenu(javafx.event.ActionEvent actionEvent) throws IOException {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("menu.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }




}
