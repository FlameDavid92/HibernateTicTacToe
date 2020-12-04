package it.corsobackend.HibernateTicTacToe.models;

import java.util.Random;
import java.util.stream.IntStream;

public class TrisGame2 {
    public int trisGame;
    private final boolean playerX;
    private int lastPlayerPos;
    private int lastServerPos;
    private int movesCounter;

    public TrisGame2(boolean playerX){
        this.playerX = playerX;
        lastPlayerPos = 0;
        lastServerPos = 0;
        movesCounter = 0;
        trisGame = 0;
    }

    public TrisGame2(int trisGame, boolean playerX, int lastPlayerPos, int lastServerPos, int movesCounter){
        this.trisGame = trisGame;
        this.playerX = playerX;
        this.lastPlayerPos = lastPlayerPos;
        this.lastServerPos = lastServerPos;
        this.movesCounter = movesCounter;
    }

    public int getTrisGame() {
        return trisGame;
    }

    public int getLastPlayerPos() {
        return lastPlayerPos;
    }

    public int getLastServerPos() {
        return lastServerPos;
    }

    public boolean isPlayerX() {
        return playerX;
    }

    public int getMovesCounter() {
        return movesCounter;
    }

    public boolean gioca(int i, int j){
        if(movesCounter >=9 || i>=3 || i<0 || j>=3 || j<0) return false;

        int limit = (i*3)+j;
        int checkPos;
        if(limit == 0) checkPos = 1;
        else checkPos = IntStream.range(0,limit).reduce(1,(a,b) -> a*10);

        if((trisGame/checkPos)%10 == 0){
            movesCounter++;
            lastPlayerPos = (checkPos * (playerX ? 2 : 1));
            trisGame += lastPlayerPos;
            if(movesCounter<9){
                serverMove();
            }
            return true;
        } return false;
    }

    private void serverMove(){
        Random rndm = new Random();
        int i;
        int j;
        int checkPos;
        do{
            i = rndm.nextInt(3);
            j = rndm.nextInt(3);
            checkPos = IntStream.range(0,(i*3)+j).reduce(1,(a,b) -> a*10);
        }while((trisGame/checkPos)%10 != 0);
        movesCounter++;
        lastServerPos = (checkPos * (playerX ? 1 : 2));
        trisGame += lastServerPos;
    }

    public boolean back(){
        if(lastPlayerPos != 0){
            trisGame -= lastPlayerPos;
            trisGame -= lastServerPos;
            lastPlayerPos = 0;
            lastServerPos = 0;
            return true;
        } return false;
    }

    public String getViewGame(){
        StringBuilder str = new StringBuilder();
        str.append("Tuo simbolo: ").append((isPlayerX()) ? "X" : "O").append("\n");
        int tempInt = trisGame;
        int val;
        for(int i=8; i>=0; i--){
            str.append("|");
            val = tempInt%10;
            str.append((val == 2) ? "X" : ((val == 1) ? "O" : " "));
            tempInt /= 10;
            str.append("|");
            if(i%3 == 0) str.append("\n");
        }
        return str.toString();
    }
}
