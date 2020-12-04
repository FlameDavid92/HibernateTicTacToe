package it.corsobackend.HibernateTicTacToe.models;

import java.util.Random;

public class TrisGame {
    public enum ValoreCella{VUOTA,X,O}
    public enum GameResp{INDEXERR, NOTVOIDERR, PLAYERWIN, SERVERWIN, TIE, CONTINUE}

    private final ValoreCella[][] game;
    private final ValoreCella simboloPlayer;
    private final ValoreCella simboloServer;
    private int lastPlayerI = -1;
    private int lastPlayerJ = -1;
    private int lastServerI = -1;
    private int lastServerJ = -1;
    private int movesCounter;

    public TrisGame(ValoreCella simboloPlayer){
        this.simboloPlayer = simboloPlayer;
        this.simboloServer = (simboloPlayer == ValoreCella.O) ? ValoreCella.X : ValoreCella.O;
        movesCounter = 0;
        game = new ValoreCella[3][3];
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                game[i][j] = ValoreCella.VUOTA;
            }
        }
    }

    public TrisGame(String game, Character simboloPlayer, Integer movesCounter, String lastMoves){
        this.movesCounter = movesCounter;
        this.simboloPlayer = (simboloPlayer == 'x') ? ValoreCella.X : ValoreCella.O;
        this.simboloServer = (simboloPlayer == 'x') ? ValoreCella.O : ValoreCella.X;
        String[] gameArrStr = game.split(";");
        this.game = new ValoreCella[3][3];
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                String s = gameArrStr[(i*3)+j];
                if(s.equals("o")){
                    this.game[i][j] = ValoreCella.O;
                }else if(s.equals("x")){
                    this.game[i][j] = ValoreCella.X;
                }else{
                    this.game[i][j] = ValoreCella.VUOTA;
                }
            }
        }
        String[] arrLastMoves = lastMoves.split(";");
        this.lastPlayerI = Integer.parseInt(arrLastMoves[0]);
        this.lastPlayerJ = Integer.parseInt(arrLastMoves[1]);
        this.lastServerI = Integer.parseInt(arrLastMoves[2]);
        this.lastServerJ = Integer.parseInt(arrLastMoves[3]);
    }

    public ValoreCella getSimboloPlayer() {
        return simboloPlayer;
    }

    public int getMovesCounter() {
        return movesCounter;
    }

    public int getLastPlayerI() {
        return lastPlayerI;
    }

    public int getLastPlayerJ() {
        return lastPlayerJ;
    }

    public int getLastServerI() {
        return lastServerI;
    }

    public int getLastServerJ() {
        return lastServerJ;
    }

    public GameResp gioca(int i, int j){
        if(i>=3 || i<0 || j>=3 || j<0) return GameResp.INDEXERR;
        if(game[i][j] == ValoreCella.VUOTA){
            movesCounter++;
            game[i][j] = simboloPlayer;
            lastPlayerI = i;
            lastPlayerJ = j;
            if(checkEndGame()) return GameResp.PLAYERWIN;
            if(movesCounter<9) {
                serverMove();
                if(checkEndGame()) return GameResp.SERVERWIN;
            }else if(movesCounter == 9) return GameResp.TIE;
            return GameResp.CONTINUE;
        } return GameResp.NOTVOIDERR;
    }

    private void serverMove(){
        Random rndm = new Random();
        int i;
        int j;
        do{
            i = rndm.nextInt(3);
            j = rndm.nextInt(3);
        }while(game[i][j] != ValoreCella.VUOTA);
        movesCounter++;
        game[i][j] = simboloServer;
        lastServerI = i;
        lastServerJ = j;
    }

    public boolean checkEndGame(){
        return (game[0][0] != ValoreCella.VUOTA && game[0][0] == game[0][1] && game[0][1] == game[0][2]) || //righe
                (game[1][0] != ValoreCella.VUOTA && game[1][0] == game[1][1] && game[1][1] == game[1][2]) ||
                (game[2][0] != ValoreCella.VUOTA && game[2][0] == game[2][1] && game[2][1] == game[2][2]) ||
                (game[0][0] != ValoreCella.VUOTA && game[0][0] == game[1][0] && game[1][0] == game[2][0]) || //colonne
                (game[0][1] != ValoreCella.VUOTA && game[0][1] == game[1][1] && game[1][1] == game[2][1]) ||
                (game[0][2] != ValoreCella.VUOTA && game[0][2] == game[1][2] && game[1][2] == game[2][2]) ||
                (game[0][0] != ValoreCella.VUOTA && game[0][0] == game[1][1] && game[1][1] == game[2][2]) || //diagonali
                (game[2][0] != ValoreCella.VUOTA && game[2][0] == game[1][1] && game[1][1] == game[0][2]);
    }

    public boolean back(){
        if(lastPlayerI != -1 && lastPlayerJ != -1){
            game[lastPlayerI][lastPlayerJ] = ValoreCella.VUOTA;
            game[lastServerI][lastServerJ] = ValoreCella.VUOTA;
            lastPlayerI = -1;
            lastPlayerJ = -1;
            lastServerI = -1;
            lastServerJ = -1;
            return true;
        } return false;
    }

    public String getLastMoves(){
        return lastPlayerI+";"+lastPlayerJ+";"+lastServerI+";"+lastServerJ;
    }

    public String getDAOGame(){
        StringBuilder str = new StringBuilder();
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                switch (game[i][j]){
                    case VUOTA -> str.append(" ");
                    case O -> str.append("o");
                    case X -> str.append("x");
                }
                if(i<2||j<2) str.append(";");
            }
        }
        return str.toString();
    }

    public String getViewGame(){
        StringBuilder str = new StringBuilder();
        str.append("Tuo simbolo: ").append(simboloPlayer).append("\n");
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                str.append("|");
                if(game[i][j] == ValoreCella.VUOTA) str.append(" ");
                else if(game[i][j] == ValoreCella.X) str.append("X");
                else str.append("O");
                str.append("|");
            }
            str.append("\n");
        }
        return str.toString();
    }
}
