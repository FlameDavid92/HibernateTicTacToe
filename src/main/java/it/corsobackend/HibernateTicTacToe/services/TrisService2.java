package it.corsobackend.HibernateTicTacToe.services;

import it.corsobackend.HibernateTicTacToe.models.TrisGame2;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class TrisService2 {
    /************************Versione minimale con matrice di gioco rappresentata da un solo intero.********************************/
    /*In questo caso parità, perdita, vittoria e conclusione partita non sono gestite lato server!*/
    public static HashMap<Integer, TrisGame2> games2 = new HashMap<>();

    public String statoAttuale2(int idPartita){
        TrisGame2 game = games2.get(idPartita);
        if(game == null) return "Vai a /"+idPartita+"/new per iniziare un nuovo gioco!\n";
        return game.stringGame();
    }

    public String back2(int idPartita){
        TrisGame2 game = games2.get(idPartita);
        if(game == null) return "Vai a /"+idPartita+"/new per iniziare un nuovo gioco!\n";
        String ret = "";
        if(!game.back()){
            ret+="Mossa non disponibile!\n";
        }
        return ret+game.stringGame();
    }

    public String nuovoGioco2(int idPartita, String simboloPlayer){
        if(simboloPlayer.length() > 1) return "Simbolo player non corretto!";

        boolean playerX;
        if(simboloPlayer.toLowerCase().equals("o")) playerX = false;
        else if(simboloPlayer.toLowerCase().equals("x")) playerX = true;
        else return "Simbolo player non corretto!";

        String ret="";
        TrisGame2 nuovoGioco = new TrisGame2(playerX);
        if(games2.put(idPartita, nuovoGioco) == null){
            ret+="Nuova partita iniziata!\n";
        }else{
            ret+="Partita riavviata!\n";
        }
        return ret+nuovoGioco.stringGame();
    }

    public String gioca2(int idPartita, int i, int j){
        TrisGame2 game = games2.get(idPartita);
        if(game == null) return "Vai a /"+idPartita+"/new per iniziare un nuovo gioco!\n";
        String ret = "";
        if(!game.gioca(i, j)){
            System.out.println("movesCounter: "+game.getMovesCounter());
            ret += "Cella già occupata o indici non corretti o partita conclusa(tutte le celle occupate)!\n";
        }
        return ret+game.stringGame();
    }

    /***********Prova generazione matrice di gioco dato un intero************/
    public String[][] generaMatrice(int interoMatrice){
        String[][] matrice = new String[3][3];
        int tempInt = interoMatrice;
        int val;
        for(int i=8; i>=0; i--){
            val = tempInt%10;
            matrice[i/3][i%3] = (val == 2) ? "X" : ((val == 1) ? "O" : " ");
            tempInt /= 10;
        }
        return matrice;
    }
}

