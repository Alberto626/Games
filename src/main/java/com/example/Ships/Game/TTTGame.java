package com.example.Ships.Game;

public class TTTGame {//TODO, add gametype and status to be ENUMS LATER
    private long id;
    private long firstPlayerID;
    private long secondPlayerID;//in database, this is nullable, but in entity null == 0
    private String created;
    private String gameType; //BOT, PVP
    private char firstPlayerPiece;
    private String gameStatus;//IN PROGRESS, HOLD

    public String getGameStatus() {
        return gameStatus;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFirstPlayerID() {
        return firstPlayerID;
    }

    public void setFirstPlayerID(long firstPlayerID) {
        this.firstPlayerID = firstPlayerID;
    }

    public long getSecondPlayerID() {
        return secondPlayerID;
    }

    public void setSecondPlayerID(long secondPlayerID) {
        this.secondPlayerID = secondPlayerID;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public char getFirstPlayerPiece() {
        return firstPlayerPiece;
    }

    public void setFirstPlayerPiece(char firstPlayerPiece) {
        this.firstPlayerPiece = firstPlayerPiece;
    }
    @Override
    public String toString() {
        return "hello world";
    }
}
