package it.corsobackend.HibernateTicTacToe.entities;

import javax.persistence.*;

@Entity
public class TrisGameDAO {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private String id;
    private String game;
    private Character simboloPlayer;
    private Integer movesCounter;
    private String lastMoves;

    public TrisGameDAO(){}

    public TrisGameDAO(String id, String game, Character simboloPlayer, Integer movesCounter,
                       String lastMoves){
        this.id = id;
        this.game = game;
        this.simboloPlayer = simboloPlayer;
        this.movesCounter = movesCounter;
        this.lastMoves = lastMoves;
    }

    public String getId() {
        return id;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public Character getSimboloPlayer() {
        return simboloPlayer;
    }

    public void setSimboloPlayer(Character simboloPlayer) {
        this.simboloPlayer = simboloPlayer;
    }

    public Integer getMovesCounter() {
        return movesCounter;
    }

    public void setMovesCounter(Integer movesCounter) {
        this.movesCounter = movesCounter;
    }

    public String getLastMoves() {
        return lastMoves;
    }

    public void setLastMoves(String lastMoves) {
        this.lastMoves = lastMoves;
    }
}
