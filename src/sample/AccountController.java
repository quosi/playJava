package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AccountController {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField user1Name;
    GameData itsData;
    @FXML
    private TextField userHighestScore;
    @FXML
    private TextField userLastScore;

    public AccountController(){
        itsData = GameData.getInstance();
       // itsData = new GameData();

    }
    @FXML
    public void initialize()  {
        Score score = itsData.GETPlayer1Score();
        userHighestScore.setText(Integer.toString(score.getPlayerHighestScore()));
        userLastScore.setText(Integer.toString(score.getPlayerLastScore()));
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
