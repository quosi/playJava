package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class PlayAgainController {

    @FXML
    private AnchorPane rootPane;
    private GamePlayModel itsModel;

    public PlayAgainController () {
        itsModel = GamePlayModel.getInstance();
    }

    public void startNewGame(javafx.event.ActionEvent actionEvent) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("main.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void Menu(javafx.event.ActionEvent actionEvent) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("menu.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void exitGame(javafx.event.ActionEvent actionEvent) {
        itsModel.SaveGameData();
        System.exit(0);
    }
}
