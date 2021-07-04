package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextField;
import java.io.IOException;

public class UserTwoController {
    private GamePlayModel itsModel;

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField colorText;

    public UserTwoController () {
        itsModel = GamePlayModel.getInstance();
    }

    @FXML
    public void initialize()  {
        if(itsModel.GETPlayer1Color() == PlayerColor.RED){
            colorText.setText("Yellow");
        }else if (itsModel.GETPlayer1Color() == PlayerColor.YELLOW) {
            colorText.setText("Red");
        }
    }
    public void showBoard(javafx.event.ActionEvent actionEvent) throws IOException {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("main.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void showUser1(javafx.event.ActionEvent actionEvent) throws IOException {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("user1.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
