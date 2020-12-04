package it.corsobackend.HibernateTicTacToe.services;

import it.corsobackend.HibernateTicTacToe.entities.TrisGameDAO;
import it.corsobackend.HibernateTicTacToe.models.TrisGame;
import it.corsobackend.HibernateTicTacToe.repositories.TrisGameRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TrisService {

    public String gioca(String auth, int i, int j, TrisGameRepository tgr){
        Optional<TrisGameDAO> optTrisGameDAO = tgr.findById(auth);
        if(optTrisGameDAO.isPresent()){
            TrisGameDAO trisGameDAO = optTrisGameDAO.get();
            TrisGame trisGame = getModelTrisGame(trisGameDAO);
            TrisGame.GameResp gameResp = trisGame.gioca(i, j);
            switch (gameResp) {
                case INDEXERR -> {
                    return "Indici non corretti!\n"+trisGame.getViewGame();
                }
                case NOTVOIDERR -> {
                    return "Cella già occupata!\n"+trisGame.getViewGame();
                }
                case PLAYERWIN -> {
                    tgr.delete(trisGameDAO);
                    return "Hai vinto!!!\n"+"Vai a /new per rigiocare!\n"+trisGame.getViewGame();
                }
                case SERVERWIN -> {
                    tgr.delete(trisGameDAO);
                    return "Hai perso!\n"+"Vai a /new per rigiocare!\n"+trisGame.getViewGame();
                }
                case TIE -> {
                    tgr.delete(trisGameDAO);
                    return "Partita conclusa in parità.\n"+"Vai a /new per rigiocare!\n"+trisGame.getViewGame();
                }
                case CONTINUE -> {
                    trisGameDAO.setGame(trisGame.getDAOGame());
                    trisGameDAO.setMovesCounter(trisGame.getMovesCounter());
                    trisGameDAO.setLastMoves(trisGame.getLastMoves());
                    tgr.save(trisGameDAO);
                    return trisGame.getViewGame();
                }
                default -> {
                    return "Vai a /new per iniziare un nuovo gioco!\n";
                }
            }
        }else{
            return "Vai a /new per iniziare un nuovo gioco!\n";
        }
    }

    public String nuovoGioco(String auth, String simboloPlayer, TrisGameRepository tgr){
        if(simboloPlayer.length() > 1) return "Simbolo player non corretto!";

        TrisGame.ValoreCella valorePlayer;
        char charSimboloPlayer;
        if(simboloPlayer.equalsIgnoreCase("o")) {
            valorePlayer = TrisGame.ValoreCella.O;
            charSimboloPlayer = 'o';
        } else if(simboloPlayer.equalsIgnoreCase("x")) {
            valorePlayer = TrisGame.ValoreCella.X;
            charSimboloPlayer = 'x';
        } else return "Simbolo player non corretto!";

        TrisGame trisGame = new TrisGame(valorePlayer);
        TrisGameDAO trisGameDAO = new TrisGameDAO(auth, trisGame.getDAOGame(),
                charSimboloPlayer, trisGame.getMovesCounter(), trisGame.getLastMoves());
        tgr.save(trisGameDAO);

        return "Nuova partita iniziata!\n"+trisGame.getViewGame();
    }

    public String back(String auth, TrisGameRepository tgr){
        Optional<TrisGameDAO> optTrisGameDAO = tgr.findById(auth);
        if(optTrisGameDAO.isPresent()){
            TrisGameDAO trisGameDAO = optTrisGameDAO.get();
            TrisGame trisGame = getModelTrisGame(trisGameDAO);
            String ret = "";
            if(!trisGame.back()){
                ret+="Mossa non disponibile!\n";
            }
            trisGameDAO.setGame(trisGame.getDAOGame());
            trisGameDAO.setMovesCounter(trisGame.getMovesCounter()-2);
            trisGameDAO.setLastMoves("-1;-1;-1;-1;");
            tgr.save(trisGameDAO);
            return ret+trisGame.getViewGame();
        }else{
            return "Vai a /new per iniziare un nuovo gioco!\n";
        }
    }

    public String statoAttuale(String auth, TrisGameRepository tgr){
        Optional<TrisGameDAO> optTrisGameDAO = tgr.findById(auth);
        if(optTrisGameDAO.isPresent()){
            TrisGameDAO trisGameDAO = optTrisGameDAO.get();
            TrisGame trisGame = getModelTrisGame(trisGameDAO);
            return trisGame.getViewGame();
        }else return "Vai a /new per iniziare un nuovo gioco!\n";
    }

    private TrisGame getModelTrisGame(TrisGameDAO trisGameDAO){
        return new TrisGame(trisGameDAO.getGame(),
                trisGameDAO.getSimboloPlayer(),
                trisGameDAO.getMovesCounter(),
                trisGameDAO.getLastMoves());
    }
}
