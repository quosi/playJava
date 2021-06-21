package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class MenuController {

    @FXML
    private AnchorPane rootPane;

    GamePlayModel itsModel;

    public MenuController () {
        itsModel = GamePlayModel.getInstance();
    }
    public void showUser1(javafx.event.ActionEvent actionEvent) throws IOException {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("user1.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void showHelp(javafx.event.ActionEvent actionEvent) throws IOException {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("help.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void showAccount(javafx.event.ActionEvent actionEvent) throws IOException {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("account.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void exit() {
        itsModel.SaveGameData();
        System.exit(0);
    }


}
