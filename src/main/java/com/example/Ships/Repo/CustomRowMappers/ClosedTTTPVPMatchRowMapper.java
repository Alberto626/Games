package com.example.Ships.Repo.CustomRowMappers;

import com.example.Ships.Game.APIObjects.ClosedTTTPVPMatch;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClosedTTTPVPMatchRowMapper implements RowMapper<ClosedTTTPVPMatch> {
    @Override
    public ClosedTTTPVPMatch mapRow(ResultSet rs, int rowNum) throws SQLException {
        ClosedTTTPVPMatch closedTTTPVPMatches = new ClosedTTTPVPMatch();
        closedTTTPVPMatches.setCreated(rs.getString("Created"));
        closedTTTPVPMatches.setGameID(rs.getLong("id"));
        closedTTTPVPMatches.setGameStatus(rs.getString("GameStatus"));
        closedTTTPVPMatches.setFirstPlayerPiece(rs.getString("FirstPlayerPiece").charAt(0));
        closedTTTPVPMatches.setFirstPlayer(rs.getString("FirstPlayer"));
        closedTTTPVPMatches.setSecondPlayer(rs.getString("SecondPlayer"));
        return closedTTTPVPMatches;
    }
}
