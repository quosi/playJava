package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.*;



public class Main extends Application {

    GamePlayModel myGamePlayModel;
    GameData myGameData;

    @Override
    public void start(Stage primaryStage) throws Exception{

        myGamePlayModel = GamePlayModel.getInstance();
        myGameData = GameData.getInstance();

        myGamePlayModel.SETItsGameDataObject(myGameData);
        myGameData.loadGameData();

        try {
            //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("welcome1.fxml"));
            //Parent root1 = (Parent) fxmlLoader.load();
            Parent root1 = FXMLLoader.load(getClass().getResource("welcome1.fxml"));

            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Fantastic Four Game");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static void main(String[] args)  {
        launch(args);

    }
}
