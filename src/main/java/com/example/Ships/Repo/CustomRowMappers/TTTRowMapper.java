package com.example.Ships.Repo.CustomRowMappers;

import com.example.Ships.Game.TTTGame;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TTTRowMapper implements RowMapper<TTTGame> {
    @Override
    public TTTGame mapRow(ResultSet rs, int rowNum) throws SQLException {
        TTTGame tttGame = new TTTGame();
        tttGame.setId(rs.getLong("id"));
        tttGame.setFirstPlayerID(rs.getLong("FirstPlayerID"));
        tttGame.setSecondPlayerID(rs.getLong("SecondPlayerID"));
        tttGame.setCreated(rs.getString("Created"));
        tttGame.setGameType(rs.getString("GameType"));
        tttGame.setFirstPlayerPiece(rs.getString("FirstPlayerPiece").charAt(0));
        tttGame.setGameStatus(rs.getString("GameStatus"));
        return tttGame;
    }
}
