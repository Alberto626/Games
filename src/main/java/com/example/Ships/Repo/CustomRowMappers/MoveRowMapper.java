package com.example.Ships.Repo.CustomRowMappers;

import com.example.Ships.Game.Entities.Move;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MoveRowMapper implements RowMapper<Move> {
    @Override
    public Move mapRow(ResultSet rs, int rowNum) throws SQLException {
        Move move = new Move();
        move.setId(rs.getLong("id"));
        move.setPlayerID(rs.getLong("PlayerID"));
        move.setGameID(rs.getLong("GameID"));
        move.setBoardRow(rs.getInt("BoardRow"));
        move.setBoardColumn(rs.getInt("BoardColumn"));
        move.setCreated(rs.getString("Created"));

        return move;
    }
}
