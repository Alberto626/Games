package com.example.Ships.Game.WinCondition;

import java.util.List;

import com.example.Ships.Game.Entities.Move;

public class TTTWinCondition{
    private List<Move> moves;
    private char[][] gameBoard;
    private char winner;
    public TTTWinCondition(List<Move> moves) {
        this.moves = moves;
        gameBoard = new char[3][3];
        fillBoard();
    }
    public boolean isWinner() {
        if(moves.size() < 5 ) {//not possible to win in less than 5 moves in total
            return false;
        }
        if(horizontal() || vertical() || rightDiagonal() || leftDiagonal()) {
            return true;
        } 
        return false;
    }
    private boolean horizontal() {
        for(int x = 0; x < gameBoard.length; x++) {
            if(gameBoard[x][0] == gameBoard[x][1] && gameBoard[x][1] == gameBoard[x][2]) {
                if(gameBoard[x][0] != '\u0000') {// not the default value
                    winner = gameBoard[x][0];
                    return true;
                }
            }
        }
        return false;
    }
    private boolean vertical() {
        for(int x = 0; x < gameBoard[0].length; x++) {
            if(gameBoard[0][x] == gameBoard[1][x] && gameBoard[1][x] == gameBoard[2][x]) {
                if(gameBoard[0][x] != '\u0000') {
                    winner = gameBoard[0][x];
                    return true;
                }
            }

        }
        return false;
    }
    private boolean rightDiagonal() {
        if(gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2]) {
            if(gameBoard[0][0] != '\u0000') {
                winner = gameBoard[0][0];
                return true;
            }
        }
        return false;
    }
    private boolean leftDiagonal() {
        if(gameBoard[0][2] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][0]) {
            if(gameBoard[0][2] != '\u0000') {
                winner = gameBoard[0][2];
                return true;
            }
        }
        return false;
    }
    private void fillBoard() {
        for(int x = 0; x < moves.size(); x++) {
            int col = moves.get(x).getBoardColumn() -1;
            int row = moves.get(x).getBoardRow() -1;
            if(x%2 == 0) {//x
                gameBoard[row][col] = 'X';
            }
            else {
                gameBoard[row][col] = 'O';
            }
        }
    }
    public char getWinner() {
        return winner;
    }
}