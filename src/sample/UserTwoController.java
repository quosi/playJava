package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class UserTwoController {
    @FXML
    private AnchorPane rootPane;

    public void showBoard(javafx.event.ActionEvent actionEvent) throws IOException {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("main.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
