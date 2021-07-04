package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.*;

/**
 * controller class to manage the game play view and handle user inputs.
 */
public class BoardController implements IGamePlayObserver{
    private GamePlayModel itsModel;

    @FXML
    private AnchorPane rootPane; /**< main screen javafx node */
    @FXML
    private Circle c1; /**< player 1 color circle */
    @FXML
    private Circle c2; /**< player 2 color circle */
    @FXML
    private Circle TurnCircle; /**< turn color circle */
    @FXML
    private GridPane gridPane; /**< Connect4 board javafx node */
    @FXML
    private TextField roundTextField; /**< round number text field */
    @FXML
    private TextField P1Score; /**< player 1 score text field */
    @FXML
    private TextField P2Score; /**< player 2 score text field */

    /**
     * Boardcontroller constructor
     * gets access to the game play model
     */
    public BoardController () {
        itsModel = GamePlayModel.getInstance();
    }

    @FXML
    /**
     * Boardcontroller javafx initialize function
     * Sets player 1 color and player 2 color
     * registers itself at the game play model as the observer to game play events and then notifies the game play
     * model to start game play
     */
    public void initialize()  {
        if(itsModel.GETPlayer1Color() == PlayerColor.RED){
            c1.setFill(Color.RED);
            c2.setFill(Color.YELLOW);

        }else if (itsModel.GETPlayer1Color() == PlayerColor.YELLOW) {
            c1.setFill(Color.YELLOW);
            c2.setFill(Color.RED);
        }

        // Should be called before start new game to set the observer for the model
        // interface can not make objects so we implement the interface in a class that can make object

        itsModel.SETGamePlayerObserver(this);

        itsModel.StartNewGame();

    }

    /**
     * Leaves the game play screen and returns to the main menu of the game
     * @param actionEvent javafx action received from the user
     */
    public void showMenu(javafx.event.ActionEvent actionEvent) throws IOException {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("menu.fxml"));
            rootPane.getChildren().setAll(pane);
            itsModel.resetGameData();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @FXML
    /**
     * handles the event when the users clicks on one of the cells of the game play board
     * Calls the game play model function ChangeStatus with the column clicked by the user
     * @param e javafx mouse event of the clicked cell
     */
    public void cellClicked (javafx.scene.input.MouseEvent e){
        try {
            ArrayList<Node> a;
            Node source = (Node)e.getSource();
            Integer colIndex = gridPane.getColumnIndex(source);
            itsModel.ChangeStatus(colIndex);
        } catch (Exception exc) {
            System.out.println(exc);
        }
    }

    @Override
    /**
     * handles the game play model to change the current turn of the player
     * Implements the observer interface method SETIsPlayer1CurrentTurn which handles the game play model event to
     * change the current turn of the player. The method will be called by the game play model to change the current
     * turn. It will check the flag isPlayer1CurrTurn and based on it sets the color of the turn circle to be either
     * for player 1 or player 2
     * @param isPlayer1CurrTurn flag to indicate if the current turn is for player 1 or 2
     */
    public void SETIsPlayer1CurrentTurn(boolean isPlayer1CurrTurn) {
        if(isPlayer1CurrTurn) {
            if(itsModel.GETPlayer1Color() == PlayerColor.RED)
                TurnCircle.setFill(Color.RED);
            else if (itsModel.GETPlayer1Color() == PlayerColor.YELLOW)
                TurnCircle.setFill(Color.YELLOW);
        } else {
            if(itsModel.GETPlayer1Color() == PlayerColor.RED)
                TurnCircle.setFill(Color.YELLOW);
            else if (itsModel.GETPlayer1Color() == PlayerColor.YELLOW)
                TurnCircle.setFill(Color.RED);
        }

    }


    /**
     * gets the node at certain column and row inside the game play board
     * @param ColIndex column index of the cell
     * @param RowIndex row index of the cell
     * @return reference to the node
     */
    private Node getNodeByRowColumnIndex (Integer ColIndex,Integer RowIndex) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            Integer nodeRow = gridPane.getRowIndex(node);
            Integer nodeCol = gridPane.getColumnIndex(node);

            if (nodeRow == null) nodeRow = 0;
            if (nodeCol == null) nodeCol = 0;

            if(nodeRow == RowIndex && nodeCol ==ColIndex ) {
                result = node;
                break;
            }
        }

        return result;
    }


    /**
     * sets the color of a certain node at certain column and row with a certain color inside the game play board
     * Implements the observer interface method SETCellColor which handles the game play model event to
     * change the color of certain node in the game play board. The method will be called by the game play model to
     * change the color of a certain node in the game play board.
     * @param ColIndex column index of the node
     * @param RowIndex row index of the node
     * @param ColIndex color to be given to the node
     */
    @Override
    public  void  SETCellColor(Integer ColIndex,Integer RowIndex ,PlayerColor cellColor){
        Node node = getNodeByRowColumnIndex(ColIndex, RowIndex);
        Circle cx = (Circle) node;
        if (cellColor == PlayerColor.RED) {
            cx.setFill(Color.RED);
        } else if (cellColor == PlayerColor.YELLOW) {
            cx.setFill(Color.YELLOW);
        } else {
            /* do nothing */
        }
    }

    /**
     * displays the win banner for the winner of the game in case of win condition
     * Implements the observer interface method PrintIfWin which handles the game play model event game won.
     */
    @Override

    public void PrintIfWin () throws IOException {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("PlayAgain.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    /**
     * updates the game play data, current round, current player 1 score, and current player 2 score
     * Implements the observer interface method updateGameData which handles the game play model data update.
     */
    public void updateGameData(int round, int p1score, int p2score) {
        roundTextField.setText(String.valueOf(round));
        P1Score.setText(String.valueOf(p1score));
        P2Score.setText(String.valueOf(p2score));
    }

}
