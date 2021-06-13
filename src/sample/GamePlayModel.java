package sample;
import sample.PlayerColor;
/*
    GamePlayModel is implemented as Singleton design pattern,
    Singleton means that the object is created only once and used by many
    other classes
    Check the following link https://www.geeksforgeeks.org/singleton-class-java

    We have created here the GamePlayModel as Singleton class.
    The Class will have a static reference to single instance.
    There reference is initialized to null.
    The constructor and the instance reference will be private so that no other
    one outside the class can create an instance.
    There will be a function called getInstance, the function will be defined as
    public static, so that anyone outside the class can call it.
    When the function is called for the first time in the code it will create an instance.
    Then each time the function is called again it will return the created instance during
    the first time.
 */

import java.sql.Array;
import java.sql.SQLOutput;

public class GamePlayModel {
    private static GamePlayModel myOnlyObject = null;
    private PlayerColor PLayer1Color;
    private PlayerColor Player2Color;
    private int Player1CurrentScore;
    private int Player2CurrentScore;

    private int ColumnsCount = 7;
    private int RowsCount = 6;

    private int round;

    private int rows;
    private int columns;

    private boolean IsCurrentTurnPlayer1;

    PlayerColor BoardGrid[][];

    private GameData itsGameData;

    private IGamePlayObserver gamePlayerObserver = null;

    private GamePlayModel() {
        PLayer1Color = null;
        Player2Color = null;
    }

// object in itself to be called
    public static GamePlayModel getInstance()
    {
        if (myOnlyObject == null)
            myOnlyObject = new GamePlayModel();

        return myOnlyObject;
    }

    //set color method
    public void SETPlayer1Color(PlayerColor p1color) {
        switch (p1color) {
            case YELLOW:
                PLayer1Color = PlayerColor.YELLOW;
                Player2Color = PlayerColor.RED;
                break;

            case RED:
                PLayer1Color = PlayerColor.RED;
                Player2Color = PlayerColor.YELLOW;
                break;

            default:
                PLayer1Color = null;
                Player2Color = null;
                break;
        }

        if (PLayer1Color == PlayerColor.RED) {
            System.out.println("Player 1 color is RED");
            System.out.println("Player 2 color is YELLOW");
        } else if (PLayer1Color == PlayerColor.YELLOW) {
            System.out.println("Player 1 color is YELLOW");
            System.out.println("Player 2 color is RED");
        }
    }

    public PlayerColor GETPlayer1Color() {
        return PLayer1Color;
    }

    public PlayerColor GETPlayer2Color() {
        return Player2Color;
    }

    void SETItsGameDataObject(GameData gameDataRef) {
        itsGameData = gameDataRef;
    }




    // Initialize observer
    public void SETGamePlayerObserver(IGamePlayObserver observer) {
        gamePlayerObserver = observer;
    }


    public void StartNewGame(){
        BoardGrid = new PlayerColor[RowsCount][ColumnsCount];

        // initialize Turn
        IsCurrentTurnPlayer1 = true;

        if (null != gamePlayerObserver)
            gamePlayerObserver.SETIsPlayer1CurrentTurn(IsCurrentTurnPlayer1);
     }

    public void ChangeStatus(Integer ColIndex) {
        if (ColIndex == null) ColIndex = 0;
        Integer RowIndex = 0;

        if (ColIndex >= ColumnsCount) return;

        RowIndex = GetHighstIndexOfFreeCell(ColIndex);

        if (RowIndex != -1) {
            if (IsCurrentTurnPlayer1) {
                BoardGrid[RowIndex][ColIndex] = PLayer1Color;
                if (null != gamePlayerObserver)
                    gamePlayerObserver.SETCellColor(ColIndex, RowIndex, PLayer1Color);
            } else {
                BoardGrid[RowIndex][ColIndex] = Player2Color;
                if (null != gamePlayerObserver)
                    gamePlayerObserver.SETCellColor(ColIndex, RowIndex, Player2Color);
            }
//change turn and color of the turn circle
            IsCurrentTurnPlayer1 = !IsCurrentTurnPlayer1;

            if (null != gamePlayerObserver)
                gamePlayerObserver.SETIsPlayer1CurrentTurn(IsCurrentTurnPlayer1);
        }
        boolean IsWin = checkWin (ColIndex, RowIndex);
    }

    private int GetHighstIndexOfFreeCell(int CollIndex ){
        for(int i = RowsCount - 1; i >= 0 ; i--){
            if(BoardGrid[i][CollIndex] == null){
                return i;
            }
        }
        return -1;
    }

    private boolean checkWin (int ColIndex,int  RowIndex){
        if(RowIndex <= 2 ) {
            if (BoardGrid [RowIndex][ColIndex] == BoardGrid [RowIndex + 1][ColIndex] || BoardGrid [RowIndex][ColIndex] == BoardGrid [RowIndex - 1][ColIndex]) {
                if(BoardGrid [RowIndex + 1][ColIndex]== BoardGrid [RowIndex + 2][ColIndex] || BoardGrid [RowIndex - 1][ColIndex]== BoardGrid [RowIndex - 2][ColIndex]){
                    if(BoardGrid [RowIndex + 2][ColIndex]== BoardGrid [RowIndex + 3][ColIndex] || BoardGrid [RowIndex - 2][ColIndex]== BoardGrid [RowIndex - 3][ColIndex]){
                return true;
            }}}


        } else if (BoardGrid [RowIndex][ColIndex] == BoardGrid [RowIndex][ColIndex + 1]){

        }else if (BoardGrid [RowIndex][ColIndex] == BoardGrid [RowIndex][ColIndex - 1]) {

        }else if (BoardGrid [RowIndex][ColIndex] == BoardGrid [RowIndex +1][ColIndex + 1]) {

        }else if (BoardGrid [RowIndex][ColIndex] == BoardGrid [RowIndex -1 ][ColIndex - 1]) {

        }else if (BoardGrid [RowIndex][ColIndex] == BoardGrid [RowIndex-1][ColIndex + 1]) {

        }else if (BoardGrid [RowIndex][ColIndex] == BoardGrid [RowIndex +1][ColIndex - 1]) {

        }else return false;


        return false;
    }

}

