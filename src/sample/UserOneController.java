package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;

import sample.PlayerColor;
import sample.GamePlayModel;

public class UserOneController {
    @FXML
    private AnchorPane rootPane;
    private GamePlayModel itsModel;


    public UserOneController () {
        itsModel = GamePlayModel.getInstance();
    }

    public void showuser2(javafx.event.ActionEvent actionEvent) throws IOException {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("user2.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void chooseRed(javafx.event.ActionEvent actionEvent) {

        itsModel.SETPlayer1Color(PlayerColor.RED);
    }

    public void chooseYellow(javafx.event.ActionEvent actionEvent) {

        itsModel.SETPlayer1Color(PlayerColor.YELLOW);
    }
}
