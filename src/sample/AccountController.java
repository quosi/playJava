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
    private TextField p1HighestScore;
    @FXML
    private TextField p1LastScore;
    @FXML
    private TextField p2HighestScore;
    @FXML
    private TextField p2LastScore;

    public AccountController(){
        itsData = GameData.getInstance();

    }
    @FXML
    public void initialize()  {
        Score score = itsData.GETPlayer1Score();
        p1HighestScore.setText(Integer.toString(score.getPlayerHighestScore()));
        p1LastScore.setText(Integer.toString(score.getPlayerLastScore()));
        
        score = itsData.GETPlayer2Score();
        p2HighestScore.setText(Integer.toString(score.getPlayerHighestScore()));
        p2LastScore.setText(Integer.toString(score.getPlayerLastScore()));
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
