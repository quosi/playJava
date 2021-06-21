package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * model class to save and load game data on HDD.
 * The class is implemented as singleton pattern, to allow single game data model in the whole
 * game. Also this allows accessing the game data model reference from any other class.
 */
public class GameData {
    private static GameData mySingletonInstance = null; /**< Instance reference of the singleton pattern */
    private int player1LastScore; /**< Player 1 last score */
    private int player2LastScore; /**< Player 2 last score */
    private int player1HighestScore; /**< Player 1 highest score */
    private int player2HighestScore; /**< Player 2 highest score */
    private String filepath = "Data1"; /**< Path to the data file holding the game data stored on the hdd */


    /**
     * GameData private constructor.
     * The constructor is created private to disallow any external class to instantiate an object of the GameData class
     * so that the class can be created only once, a singleton.
     */
    private GameData () { }

    /**
     * creates a singleton instance of GameData if not created and returns the instance to caller.
     * @return The singleton instance of GameData class
     */
    public static GameData getInstance() {
        if (mySingletonInstance == null)
            mySingletonInstance = new GameData();

        return mySingletonInstance;
    }

    /**
     * loads the game data from the HDD.
     * If the game data file is not created, it creates a new one with empty data.
     * If the game data file is existing it reads the stored data.
     */
    public void loadGameData() throws FileNotFoundException {

        try {
            File Data1 = new File(filepath);

            String absolute = Data1.getAbsolutePath();
            System.out.println("Loading system data from file: " + absolute);

            if (!Data1.exists()) {

                if (!Data1.createNewFile()) {
                    System.out.println("Failed to create a game data file");
                    return;
                }
                FileWriter myWriter = new FileWriter(Data1, true);
                myWriter.write(String.valueOf(player1LastScore) + "\n"); // Player 1 Last Score
                myWriter.write(String.valueOf(player2LastScore) + "\n"); // Player 2 Last Score
                myWriter.write(String.valueOf(player1HighestScore) + "\n"); // Player 1 Highest Score
                myWriter.write(String.valueOf(player2HighestScore) + "\n"); // Player 2 Highest Score
                myWriter.close();
                return;
            }

            Scanner myReader = new Scanner(Data1);
            player1LastScore = myReader.nextInt();
            player2LastScore = myReader.nextInt();
            player1HighestScore = myReader.nextInt();
            player2HighestScore = myReader.nextInt();
            //System.out.println(player1HighestScore);
            myReader.close();

        } catch (Exception e) {

        }
    }

    /**
     * updates the game data with the current score of player 1 and player 2.
     * If player 1 current score is greater than highest score it updates the last score with the current score,
     * same for player 2. And then it stores player 1 and 2 current score as the last score.
     * @param P1CurrScore player 1 current score as stored in the game model.
     * @param P1CurrScore player 2 current score as stored in the game model.
     */
    public void updateGameData(int P1CurrScore, int P2CurrScore) {
        player1LastScore = P1CurrScore ;
        player2LastScore = P2CurrScore;
        if(P1CurrScore > player1HighestScore){
            player1HighestScore = P1CurrScore;
        }
        if(P2CurrScore > player2HighestScore){
            player2HighestScore = P2CurrScore;
        }
    }

    /**
     * saves player 1 and player 2 data to the HDD.
     */
    public void saveGameData() {
        try {
            File Data1 = new File(filepath);
            String absolute = Data1.getAbsolutePath();
            System.out.println("Saving system data to file: " + absolute);
            FileWriter myWriter = new FileWriter(Data1);
            myWriter.write(String.valueOf(player1LastScore) + "\n"); // Player 1 Last Score
            myWriter.write(String.valueOf(player2LastScore) + "\n"); // Player 2 Last Score
            myWriter.write(String.valueOf(player1HighestScore) + "\n"); // Player 1 Highest Score
            myWriter.write(String.valueOf(player2HighestScore) + "\n"); // Player 2 Highest Score
            myWriter.close();

        } catch (Exception e) {

        }
    }

    /**
     * returns player 1 last score and highest score in meta-class score.
     * @see // https://stackoverflow.com/questions/2832472/how-to-return-2-values-from-a-java-method
     * @return Object of meta-class score contains player 1 last and highest score
     */
    public Score GETPlayer1Score() {
        return new Score(player1LastScore, player1HighestScore);
    }

    /**
     * returns player 2 last score and highest score in meta-class score.
     * @see // https://stackoverflow.com/questions/2832472/how-to-return-2-values-from-a-java-method
     * @return Object of meta-class score contains player 2 last and highest score
     */
    public Score GETPlayer2Score() {
        return new Score(player2LastScore, player2HighestScore);
    }
}
