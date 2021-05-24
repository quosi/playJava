package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameData {
    private static GameData mySingletonInstance = null;
    private int player1LastScore;
    private int player2LastScore;
    private int player1HighestScore;
    private int player2HighestScore;

    private GameData () {

    }

    public static GameData getInstance() {
        if (mySingletonInstance == null)
            mySingletonInstance = new GameData();

        return mySingletonInstance;
    }

    public void loadGameData() throws FileNotFoundException {

        File Data1 = new File("Data1.txt");
        Scanner myReader = new Scanner(Data1);

        while (myReader.hasNextLine()) {
            player1LastScore = myReader.nextInt();
            player2LastScore = myReader.nextInt();
            player1HighestScore = myReader.nextInt();
            player2HighestScore = myReader.nextInt();
            System.out.println(player1HighestScore);
        }
        myReader.close();
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
}
