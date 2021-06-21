package sample;

import java.io.IOException;

/**
 * game play model observer interface.
 * interface can not make objects so we implement the interface in a class that can make object
 */
public interface IGamePlayObserver  {
     void SETIsPlayer1CurrentTurn(boolean isPlayer1CurrTurn);
     void updateGameData(int round, int p1score, int p2score);
     void  SETCellColor(Integer ColIndex,Integer RowIndex ,PlayerColor cellColor);
     void PrintIfWin () throws IOException;
}
