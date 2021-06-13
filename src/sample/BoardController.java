package sample;
import javafx.application.Application;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import sample.IGamePlayObserver;
import java.io.IOException;
import java.util.*;
import java.util.prefs.NodeChangeEvent;

public class BoardController implements IGamePlayObserver{
    private GamePlayModel itsModel;

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Circle c1;
    @FXML
    private Circle c2;

    @FXML
    private Circle TurnCircle;
    @FXML
    private GridPane gridPane;

    public BoardController () {
        itsModel = GamePlayModel.getInstance();
    }

    @FXML
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


    public void showMenu(javafx.event.ActionEvent actionEvent) throws IOException {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("menu.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }





    @FXML
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




    //select surrondings Cells
    private final ArrayList<Node> getSurroundingNodesList(Node mainNode, GridPane gridpane) {
        ArrayList<Node> list = new ArrayList<>();
        Integer mainNodeColumn = gridpane.getColumnIndex(mainNode);
        Integer mainNodeRow = gridpane.getRowIndex(mainNode);

        if (mainNodeColumn == null) mainNodeColumn = 0;
        if (mainNodeRow == null) mainNodeRow = 0;

        //children got in ObservableList
        ObservableList<Node> children = gridpane.getChildren();


      // -1 cause it returns 6*7 +1 which the grid itself
        for (int i = 0; i < children.size() - 1; i++) {
            Node n = children.get(i);
            Integer nodeCloumn = gridpane.getColumnIndex(n);
            Integer nodeRow = gridpane.getRowIndex(n);

            if (nodeCloumn == null) nodeCloumn = 0;
            if (nodeRow == null) nodeRow = 0;

            if (nodeRow + 1 == mainNodeRow && nodeCloumn == mainNodeColumn) {
                list.add(n);
            } else if (nodeRow - 1 == mainNodeRow && nodeCloumn == mainNodeColumn) {
                list.add(n);
            } else if (nodeCloumn + 1 == mainNodeColumn && nodeRow == mainNodeRow) {
                list.add(n);
            } else if (nodeCloumn - 1 == mainNodeColumn && nodeRow == mainNodeRow) {
                list.add(n);
            } else if (nodeCloumn + 1 == mainNodeColumn && nodeRow - 1 == mainNodeRow) {
                list.add(n);
            } else if (nodeCloumn + 1 == mainNodeColumn && nodeRow + 1 == mainNodeRow) {
                list.add(n);
            } else if (nodeCloumn - 1 == mainNodeColumn && nodeRow - 1 == mainNodeRow) {
                list.add(n);
            } else if (nodeCloumn - 1 == mainNodeColumn && nodeRow + 1 == mainNodeRow) {
                list.add(n);
            }
        }

        return list;
    }


    @Override
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


    public Node getNodeByRowColumnIndex (Integer ColIndex,Integer RowIndex) {
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
}
