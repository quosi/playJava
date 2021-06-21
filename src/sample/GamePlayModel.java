package sample;
/*
 */

import java.io.IOException;

/**
 * Game play model which implements all game rules and checks for correct game play.
 *
 * GamePlayModel is implemented as Singleton design pattern, Singleton means that the object is created only once
 * and used by many other classes
 * Check the following link https://www.geeksforgeeks.org/singleton-class-java
 *
 * We have created here the GamePlayModel as Singleton class.  The Class will have a static reference to single
 * instance.  There reference is initialized to null. The constructor and the instance reference will be private so
 * that no other one outside the class can create an instance. There will be a function called getInstance,
 * the function will be defined as  public static, so that anyone outside the class can call it. When the function
 * is called for the first time in the code it will create an instance.  Then each time the function is called again
 * it will return the created instance during the first time.
 */
public class GamePlayModel {
    private static GamePlayModel myOnlyObject = null; /**< Instance reference of the singleton pattern */
    private PlayerColor Player1Color; /**< Player 1 color selected in the color selection controller */
    private PlayerColor Player2Color; /**< Player 2 color selected in the color selection controller */
    private int Player1CurrentScore; /**< loaded from GameData during startup and incremented with each player 1 game win */
    private int Player2CurrentScore; /**< loaded from GameData during startup and incremented with each player 2 game win */
    private int ColumnsCount = 7; /**< Number of columns in the game board */
    private int RowsCount = 6; /**< Number of rows in the game board */
    private int round = 1; /**< Game round, incremented with each game win */
    private boolean IsCurrentTurnPlayer1; /**< flag to check if the current turn is for player 1 or 2 */
    private PlayerColor BoardGrid[][]; /**< The game play board showing which cells are empty or full, and with which color */
    private GameData itsGameData; /**< Reference to the GameData model */
    private IGamePlayObserver gamePlayerObserver = null; /**< Reference to the game board controller as observer to the model events */

    /**
     * GamePlayModel private constructor.
     * The constructor is created private to disallow any external class to instantiate an object of the GameData class
     * so that the class can be created only once, a singleton. It initializes the game play data as well.
     */
    private GamePlayModel() {
        Player1Color = null;
        Player2Color = null;
    }

    /**
     * @return player 1 color
     */
    public PlayerColor GETPlayer1Color() {
        return Player1Color;
    }

    /**
     * initializes reference itsGameData with a valid reference to the game data model
     * @param gameDataRef a valid reference to the game data model
     */
    void SETItsGameDataObject(GameData gameDataRef) {
        itsGameData = gameDataRef;
    }

    /**
     * creates a singleton instance of GamePlayModel if not created and returns the instance to caller.
     * @return The singleton instance of GamePlayModel class
     */
    public static GamePlayModel getInstance()
    {
        if (myOnlyObject == null)
            myOnlyObject = new GamePlayModel();

        return myOnlyObject;
    }

    /**
     * initializes reference gamePlayerObserver with a valid reference to the game play model events observer
     * @param observer a valid reference to the game play model events observer
     */
    public void SETGamePlayerObserver(IGamePlayObserver observer) {
        gamePlayerObserver = observer;
    }

    /**
     * restarts the game by resetting the number of rounds to 1.
     * This method is called by the BoardController to reset the game play.
     */
    public void resetGameData() {
        round = 1;
    }

    /**
     * sets the chip color for player 1 and 2 based on selection of player 1.
     * This method is called by the UserOneController to set the color for player 1.
     * Available game colors are yellow and red. If player 1 has selected red then player 2 will automatically be
     * assigned to yellow and vice verse.
     * @param p1color color selected by player 1
     */
    public void SETPlayer1Color(PlayerColor p1color) {
        switch (p1color) {
            case YELLOW:
                Player1Color = PlayerColor.YELLOW;
                Player2Color = PlayerColor.RED;
                break;

            case RED:
                Player1Color = PlayerColor.RED;
                Player2Color = PlayerColor.YELLOW;
                break;

            default:
                Player1Color = null;
                Player2Color = null;
                break;
        }

        if (Player1Color == PlayerColor.RED) {
            System.out.println("Player 1 color is RED");
            System.out.println("Player 2 color is YELLOW");
        } else if (Player1Color == PlayerColor.YELLOW) {
            System.out.println("Player 1 color is YELLOW");
            System.out.println("Player 2 color is RED");
        }
    }

    /**
     * starts a new game by resetting the board game to nulls, and reset current turn, then informs the observer to reset the view.
     * This method is called by the BoardController to start a new game without resetting the game play. \n
     * The function performs the following steps \n
     *  1) creates a new board game with all cells empty. \n
     *  2) reset the current turn to be for player 1. \n
     *  3) notifies the BoardController to update view that current turn is for player 1. \n
     *  4) notifies the BoardController to update view with the new round value, player 1 score, and player 2 score. \n
     */
    public void StartNewGame(){
        BoardGrid = new PlayerColor[RowsCount][ColumnsCount];

        // initialize Turn
        IsCurrentTurnPlayer1 = true;

        if (null != gamePlayerObserver) {
            gamePlayerObserver.SETIsPlayer1CurrentTurn(true);
            gamePlayerObserver.updateGameData(round, Player1CurrentScore, Player2CurrentScore);
        }
        round++;
     }

    /**
     * implements the game play rules by checking the events occur in the game when a new chip is inserted.
     * This method is called by the BoardController when one of the player inserts a new chip on the board. \n
     * This is the core function for the game play, performs the following steps \n
     *   1) Gets the location of the inserted chip on the board \n
     *     1a) takes as an argument the column selected by the user and checks the first empty row on the board \n
     *     1b) if the column is full or the column index is invalid the method terminates \n
     *   2) checks the current turn \n
     *   3) if the current turn is for player 1, it colors the current cell with player 1 color, else it uses player 2 color \n
     *   4) notifies the BoardController to update the view the current player color \n
     *   5) checks if the current chip can cause a win situation, if win condition is true \n
     *     5a) increments the score of the current player \n
     *     5b) notifies the BoardController to update the view to show win situation for current player \n
     *     5c) update the GameData model with the new score for player 1 and 2 \n
     *   6) if there no win condition, then changes the turn to the other player \n
     *   7) notifies the BoardController to update the view to change the turn to the other player \n
     * @param ColIndex index of the column selected by the current player
     */
    public void ChangeStatus(Integer ColIndex) throws IOException {
        if (ColIndex == null) ColIndex = 0;
        Integer RowIndex = 0;

        if (ColIndex >= ColumnsCount) return;

        RowIndex = GetHighstIndexOfFreeCell(ColIndex);

        if (RowIndex != -1) {
            if (IsCurrentTurnPlayer1) {
                BoardGrid[RowIndex][ColIndex] = Player1Color;
                if (null != gamePlayerObserver)
                    gamePlayerObserver.SETCellColor(ColIndex, RowIndex, Player1Color);
                boolean IsWin = checkWin (ColIndex, Player1Color);

                if(IsWin){
                    Player1CurrentScore++;
                    if (null != gamePlayerObserver)
                        gamePlayerObserver.PrintIfWin();

                    itsGameData.updateGameData(Player1CurrentScore, Player2CurrentScore);
                }
            } else {
                BoardGrid[RowIndex][ColIndex] = Player2Color;
                if (null != gamePlayerObserver)
                    gamePlayerObserver.SETCellColor(ColIndex, RowIndex, Player2Color);

                boolean IsWin = checkWin (ColIndex, Player2Color);

                if(IsWin){
                    Player2CurrentScore++;

                    if (null != gamePlayerObserver)
                        gamePlayerObserver.PrintIfWin();

                    itsGameData.updateGameData(Player1CurrentScore, Player2CurrentScore);
                }
            }
//change turn and color of the turn circle
            IsCurrentTurnPlayer1 = !IsCurrentTurnPlayer1;

            if (null != gamePlayerObserver)
                gamePlayerObserver.SETIsPlayer1CurrentTurn(IsCurrentTurnPlayer1);
        }
    }

    /**
     * triggers game data save request to GameData model on application exit.
     */
    public void SaveGameData() {
        itsGameData.updateGameData(Player1CurrentScore, Player2CurrentScore);
        itsGameData.saveGameData();
    }

    /**
     * checks the first empty row in the game board based on the selected column by the current player.
     * @param CollIndex index of the selected column by the current player.
     */
    private int GetHighstIndexOfFreeCell(int CollIndex ){
        for(int i = RowsCount - 1; i >= 0 ; i--){
            if(BoardGrid[i][CollIndex] == null){
                return i;
            }
        }
        return -1;
    }

    /**
     * checks the win conditions when a new chip is inserted in the game board.
     * performs the following steps \n
     *   1) checks the vertical win condition, checks the selected column by the current player \n
     *     1a) checks if 3 chips below the inserted chip are of the same color as the new chip then win condition is true \n
     *   2) checks horizontal win condition, checks if the row in which the chip is inserted has a win condition \n
     *     2a) checks 3 chips before the inserted chips and 3 chips after the inserted chip \n
     *     2b) if 4 chips are found in row of the same color of the current player then it is a win condition \n
     *   3) if no win condition detected, then check the right diagonal and the left diagonal \n
     *   7) if no win condition detected at all then return false \n
     * @param col the current column in which the chip is inserted.
     * @param color color of the current player
     * @return true if win condition is detected, false otherwise
     */
    private boolean checkWin(int col, PlayerColor color){

        int rows = RowsCount;
        int columns = ColumnsCount;

        for(int row = 0; row < rows; row++) {
            if(BoardGrid[row][col] != null) {
                // if this reaches 0, we have won
                int winningNumber = 3;

                // check downwards
                for(int winRow = row + 1; winRow < rows; winRow++) {
                    if(BoardGrid[winRow][col] == color) {
                        winningNumber--;
                        if(winningNumber == 0) return true;
                    } else winningNumber = 3;
                }

                winningNumber = 4;
                // look at the horizontal
                for(int winCol = col - 3; winCol <= col + 3; winCol++) {
                    if(winCol < 0) continue; // if the column number is not valid then skip the current loop and continue to the next column
                    if(winCol >= columns) break; // if the column number exceeded the board limits then break the loop

                    if(BoardGrid[row][winCol] != null && BoardGrid[row][winCol]== color) {
                        winningNumber--;
                        if(winningNumber == 0) return true;
                    } else winningNumber = 4;
                }
                if(checkDiagonal(row, col, color, false)) return true;
                if(checkDiagonal(row, col, color, true)) return true;
                return false;
            }
        }
        return false;
    }

    /**
     * checks the win conditions when a new chip is inserted in the game board in the diagonals.
     * the function can be used for right diagonal or the left diagonal through the parameter rightDiagonal.
     * In the diagonal check the row and column are incremented by 1 on each check step. In the right diagonal the
     * column is increment towards the last column, if left diagonal then the column is decremented towards the first
     * column. The reverser variable is used to either increment or decrement the index of the checked column.
     * checkDiagonal performs the following steps \n
     *   1) checks the diagonal direction and sets reverser value based on that \n
     *     1a) if right diagonal then reverser is set to 1, else reverser is set to -1 \n
     *   2) loops on the cells starting from (current chip row + 3, current chip column + 3) to (current chip row - 3, current chip column - 3) \n
     *     2a) if 4 chips are found in sequence of the same color of the current player then it is a win condition \n
     *   3) if no win condition detected at all then return false \n
     * @param col the current column in which the chip is inserted.
     * @param row the current row in which the chip is inserted.
     * @param color color of the current player
     * @param rightDiagonal flag to check whether we are checking the right diagonal or left diagonal
     * @return true if win condition is detected, false otherwise
     */
    private boolean checkDiagonal(int row, int col, PlayerColor color, boolean rightDiagonal) {
        int winningStreak = 4;
        int rows = RowsCount;
        int columns = ColumnsCount;
        int reverser;
        if (rightDiagonal) reverser = 1; else reverser = -1;

        for(int winRow = row + 3, winCol = col - (3 * reverser); winRow >= row - 3; winRow--, winCol += reverser) {
            if (!rightDiagonal) {
                if (winRow < 0 || winCol < 0) continue;
                if (winRow >= rows || winCol >= columns) break;
            } else {
                if(winRow < 0 || winCol >= columns) continue;
                if(winRow >= rows || winCol < 0) break;
            }

            if(BoardGrid[winRow][winCol] != null && BoardGrid[winRow][winCol]== color) {
                winningStreak--;
                if (winningStreak == 0) return true;
            } else winningStreak = 4;
        }
        return false;
    }

}

