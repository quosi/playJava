package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;

public class BoardController {
    @FXML
    private AnchorPane rootPane;

    public void showMenu(javafx.event.ActionEvent actionEvent) throws IOException {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("menu.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void definePlayerColor(){
    }
}
