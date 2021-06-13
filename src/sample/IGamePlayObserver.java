package sample;



// interface can not make objects so we implement the interface in a class that can make object
public interface IGamePlayObserver {
    public void SETIsPlayer1CurrentTurn(boolean isPlayer1CurrTurn);
    public  void  SETCellColor(Integer ColIndex,Integer RowIndex ,PlayerColor cellColor);
}
