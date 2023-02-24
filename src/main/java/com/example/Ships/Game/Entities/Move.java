package com.example.Ships.Game.Entities;

public class Move {
    private long id;
    private long playerId;
    private long gameID;
    private int boardRow;
    private int boardColumn;
    private String created;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPlayerID() {
        return playerId;
    }

    public void setPlayerID(long firstPlayerID) {
        this.playerId = firstPlayerID;
    }

    public long getGameID() {
        return gameID;
    }

    public void setGameID(long gameID) {
        this.gameID = gameID;
    }

    public int getBoardRow() {
        return boardRow;
    }

    public void setBoardRow(int boardRow) {
        this.boardRow = boardRow;
    }

    public int getBoardColumn() {
        return boardColumn;
    }

    public void setBoardColumn(int boardColumn) {
        this.boardColumn = boardColumn;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
