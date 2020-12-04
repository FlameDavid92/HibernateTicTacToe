package it.corsobackend.HibernateTicTacToe.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TrisGameDAO2 {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private String id;
    private Integer game;
    private Boolean playerX;
    private Integer lastPlayerPos;
    private Integer lastServerPos;
    private Integer movesCounter;

    public TrisGameDAO2(){}

    public TrisGameDAO2(String id, Integer game, Boolean playerX, Integer lastPlayerPos,
                        Integer lastServerPos, Integer movesCounter){
        this.id = id;
        this.game = game;
        this.playerX = playerX;
        this.lastPlayerPos = lastPlayerPos;
        this.lastServerPos = lastServerPos;
        this.movesCounter = movesCounter;
    }

    public String getId() {
        return id;
    }

    public Integer getGame() {
        return game;
    }

    public void setGame(Integer game) {
        this.game = game;
    }

    public Boolean getPlayerX() {
        return playerX;
    }

    public void setPlayerX(Boolean playerX) {
        this.playerX = playerX;
    }

    public Integer getLastPlayerPos() {
        return lastPlayerPos;
    }

    public void setLastPlayerPos(Integer lastPlayerPos) {
        this.lastPlayerPos = lastPlayerPos;
    }

    public Integer getLastServerPos() {
        return lastServerPos;
    }

    public void setLastServerPos(Integer lastServerPos) {
        this.lastServerPos = lastServerPos;
    }

    public Integer getMovesCounter() {
        return movesCounter;
    }

    public void setMovesCounter(Integer movesCounter) {
        this.movesCounter = movesCounter;
    }
}
