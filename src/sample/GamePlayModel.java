package sample;

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


public class GamePlayModel {
    private static GamePlayModel myOnlyObject = null;
    private PlayerColor PLayer1Color;
    private PlayerColor Player2Color;

    private GameData itsGameData;

    private GamePlayModel() {
        PLayer1Color = PlayerColor.UNINIT;
        Player2Color = PlayerColor.UNINIT;
    }

    public static GamePlayModel getInstance()
    {
        if (myOnlyObject == null)
            myOnlyObject = new GamePlayModel();

        return myOnlyObject;
    }
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

            case UNKNOWN:
                PLayer1Color = PlayerColor.UNKNOWN;
                Player2Color = PlayerColor.UNKNOWN;
                break;

            case UNINIT:
            default:
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
}
