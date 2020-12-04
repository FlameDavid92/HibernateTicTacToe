package it.corsobackend.HibernateTicTacToe.services;

import it.corsobackend.HibernateTicTacToe.entities.TrisGameDAO2;
import it.corsobackend.HibernateTicTacToe.models.TrisGame2;
import it.corsobackend.HibernateTicTacToe.repositories.TrisGame2Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrisService2 {
    /************************Versione minimale con matrice di gioco rappresentata da un solo intero.********************************/
    /*In questo caso parità, perdita, vittoria e conclusione partita non sono gestite lato server!*/

    public String statoAttuale2(String auth, TrisGame2Repository tg2r){
        Optional<TrisGameDAO2> optiTrisGameDAO2 = tg2r.findById(auth);
        if(optiTrisGameDAO2.isPresent()){
            TrisGameDAO2 trisGameDAO2 = optiTrisGameDAO2.get();
            TrisGame2 trisGame2 = getModelTrisGame2(trisGameDAO2);
            return trisGame2.getViewGame();
        }else return "Vai a /new per iniziare un nuovo gioco!\n";
    }

    public String back2(String auth, TrisGame2Repository tg2r){
        Optional<TrisGameDAO2> optiTrisGameDAO2 = tg2r.findById(auth);
        if(optiTrisGameDAO2.isPresent()){
            TrisGameDAO2 trisGameDAO2 = optiTrisGameDAO2.get();
            TrisGame2 trisGame2 = getModelTrisGame2(trisGameDAO2);
            String ret = "";
            if(!trisGame2.back()){
                ret+="Mossa non disponibile!\n";
            }
            trisGameDAO2.setGame(trisGame2.getTrisGame());
            trisGameDAO2.setMovesCounter(trisGame2.getMovesCounter()-2);
            trisGameDAO2.setLastPlayerPos(0);
            trisGameDAO2.setLastServerPos(0);
            tg2r.save(trisGameDAO2);
            return ret+trisGame2.getViewGame();
        }else{
            return "Vai a /new per iniziare un nuovo gioco!\n";
        }
    }

    public String nuovoGioco2(String auth, String simboloPlayer, TrisGame2Repository tg2r){
        if(simboloPlayer.length() > 1) return "Simbolo player non corretto!";

        boolean playerX;
        if(simboloPlayer.toLowerCase().equals("o")) playerX = false;
        else if(simboloPlayer.toLowerCase().equals("x")) playerX = true;
        else return "Simbolo player non corretto!";

        String ret="";
        TrisGame2 trisGame2 = new TrisGame2(playerX);
        TrisGameDAO2 trisGameDAO2 = getTrisGameDAO2(auth, trisGame2);
        tg2r.save(trisGameDAO2);
        return "Nuova partita iniziata!\n"+trisGame2.getViewGame();
    }

    public String gioca2(String auth, int i, int j, TrisGame2Repository tg2r){
        Optional<TrisGameDAO2> optiTrisGameDAO2 = tg2r.findById(auth);
        if(optiTrisGameDAO2.isPresent()){
            TrisGameDAO2 trisGameDAO2 = optiTrisGameDAO2.get();
            TrisGame2 trisGame2 = getModelTrisGame2(trisGameDAO2);
            String ret = "";
            if(!trisGame2.gioca(i, j)){
                ret += "Cella già occupata o indici non corretti o partita conclusa(tutte le celle occupate)!\n";
            }
            trisGameDAO2.setGame(trisGame2.getTrisGame());
            trisGameDAO2.setMovesCounter(trisGame2.getMovesCounter());
            trisGameDAO2.setLastPlayerPos(trisGame2.getLastPlayerPos());
            trisGameDAO2.setLastServerPos(trisGame2.getLastServerPos());
            tg2r.save(trisGameDAO2);
            return ret+trisGame2.getViewGame();
        }else{
            return "Vai a /new per iniziare un nuovo gioco!\n";
        }
    }

    private TrisGame2 getModelTrisGame2(TrisGameDAO2 trisGameDAO2){
        return new TrisGame2(trisGameDAO2.getGame(),trisGameDAO2.getPlayerX(),
                trisGameDAO2.getLastPlayerPos(),trisGameDAO2.getLastServerPos(),
                trisGameDAO2.getMovesCounter());
    }

    private TrisGameDAO2 getTrisGameDAO2(String auth, TrisGame2 trisGame2){
        return new TrisGameDAO2(auth, trisGame2.getTrisGame(),trisGame2.isPlayerX(),
                trisGame2.getLastPlayerPos(),trisGame2.getLastServerPos(),
                trisGame2.getMovesCounter());
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

