package sample;

//this is a meta class that used when we want to return more than one value (return usually returns one value )as the score to the accountView and model game data
// https://stackoverflow.com/questions/2832472/how-to-return-2-values-from-a-java-method


final class Score {
    private int playerLastScore;
    private int playerHighestScore;


    public Score(int l , int h){
        playerLastScore = l;
        playerHighestScore=h;
    }
    public int getPlayerLastScore() {
        return playerLastScore;
    }
    public int getPlayerHighestScore() {
        return playerHighestScore;
    }
}