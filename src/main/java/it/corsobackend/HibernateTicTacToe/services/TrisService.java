package it.corsobackend.HibernateTicTacToe.services;

import it.corsobackend.HibernateTicTacToe.models.TrisGame;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class TrisService {
    public static HashMap<String, TrisGame> games = new HashMap<>();

    public String gioca(String auth, int i, int j){
        TrisGame game = games.get(auth);
        if(game == null) return "Vai a /new per iniziare un nuovo gioco!\n";
        String ret = "";
        TrisGame.GameResp gameResp = game.gioca(i, j);
        switch (gameResp) {
            case INDEXERR -> ret += "Indici non corretti!\n";
            case NOTVOIDERR -> ret += "Cella già occupata!\n";
            case PLAYERWIN -> {
                ret += "Hai vinto!!!\n";
                ret += "Vai a /new per rigiocare!\n";
                games.put(auth, null);
            }
            case SERVERWIN -> {
                ret += "Hai perso!\n";
                ret += "Vai a /new per rigiocare!\n";
                games.put(auth, null);
            }
            case TIE -> {
                ret += "Partita conclusa in parità.\n";
                ret += "Vai a /new per rigiocare!\n";
                games.put(auth, null);
            }
        }
        return ret+game.stringGame();
    }

    public String nuovoGioco(String auth, String simboloPlayer){
        if(simboloPlayer.length() > 1) return "Simbolo player non corretto!";

        TrisGame.ValoreCella valorePlayer = null;
        if(simboloPlayer.toLowerCase().equals("o")) valorePlayer = TrisGame.ValoreCella.O;
        else if(simboloPlayer.toLowerCase().equals("x")) valorePlayer = TrisGame.ValoreCella.X;
        else return "Simbolo player non corretto!";

        String ret="";
        if(games.put(auth, new TrisGame(valorePlayer)) == null){
            ret+="Nuova partita iniziata!\n";
        }else{
            ret+="Partita riavviata!\n";
        }
        return ret+games.get(auth).stringGame();
    }

    public String back(String auth){
        TrisGame game = games.get(auth);
        if(game == null) return "Vai a /new per iniziare un nuovo gioco!\n";
        String ret = "";
        if(!game.back()){
            ret+="Mossa non disponibile!\n";
        }
        return ret+game.stringGame();
    }

    public String statoAttuale(String auth){
        TrisGame game = games.get(auth);
        if(game == null) return "Vai a /new per iniziare un nuovo gioco!\n";
        return game.stringGame();
    }
}
