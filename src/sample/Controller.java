package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;


public class Controller {

    GamePlayModel myGamePlayModel = null;
    @FXML
    private AnchorPane rootPane;

    public Controller () {
        myGamePlayModel = GamePlayModel.getInstance();
    }

    public void showLogin(javafx.event.ActionEvent actionEvent) throws IOException {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("login.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
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

    @FXML
    public void exit(javafx.event.ActionEvent actionEvent) throws IOException {
        myGamePlayModel.SaveGameData();
        System.exit(0);
    }

}
