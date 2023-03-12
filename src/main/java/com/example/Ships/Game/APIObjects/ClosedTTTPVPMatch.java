package com.example.Ships.Game.APIObjects;

public class ClosedTTTPVPMatch {
    private long gameID;
    private String created;
    private char firstPlayerPiece;
    private String firstPlayer;
    private String secondPlayer;
    private String gameStatus;

    public long getGameID() {
        return gameID;
    }

    public void setGameID(long gameID) {
        this.gameID = gameID;
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

    public String getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(String firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public String getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(String secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        gameStatus = gameStatus;
    }
}
