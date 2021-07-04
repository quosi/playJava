package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

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
    public void Menu(javafx.event.ActionEvent actionEvent) throws IOException {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("menu.fxml"));
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
