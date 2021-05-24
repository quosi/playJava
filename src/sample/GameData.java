package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import sample.Score;

public class GameData {
    private static GameData mySingletonInstance = null;
    private int player1LastScore;
    private int player2LastScore;
    private int player1HighestScore;
    private int player2HighestScore;
    private String filepath = "C:\\gamedata\\Data1";

    private GameData () {

    }

    public static GameData getInstance() {
        if (mySingletonInstance == null)
            mySingletonInstance = new GameData();

        return mySingletonInstance;
    }

    public void loadGameData() throws FileNotFoundException {

        try {
            File Data1 = new File(filepath);
            if (!Data1.exists()) {

                if (!Data1.createNewFile()) {
                    System.out.println("Failed to create a game data file");
                    return;
                }
                FileWriter myWriter = new FileWriter(Data1);
                myWriter.write("0"); // Player 1 Last Score
                myWriter.write("0"); // Player 2 Last Score
                myWriter.write("0"); // Player 1 Highest Score
                myWriter.write("0"); // Player 2 Highest Score
                myWriter.close();
                return;
            }

            Scanner myReader = new Scanner(Data1);
            player1LastScore = myReader.nextInt();
            player2LastScore = myReader.nextInt();
            player1HighestScore = myReader.nextInt();
            player2HighestScore = myReader.nextInt();
            System.out.println(player1HighestScore);
            myReader.close();

        } catch (Exception e) {

        }
    }

    void updateGameData(int P1CurrScore, int P2CurrScore) {
        player1LastScore = P1CurrScore ;
        player2LastScore = P2CurrScore;
        if(P1CurrScore > player1HighestScore){
            player1HighestScore = P1CurrScore;
        }
        if(P2CurrScore > player2HighestScore){
            player2HighestScore = P2CurrScore;
        }
    }
// https://stackoverflow.com/questions/2832472/how-to-return-2-values-from-a-java-method



    public Score GETPlayer1Score() {
        return new Score(player1LastScore, player1HighestScore);
    }

    public Score GETPlayer2Score() {
        return new Score(player2LastScore, player2HighestScore);
    }
}
