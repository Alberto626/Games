package com.example.Ships.Repo.CustomRowMappers;

import com.example.Ships.Game.APIObjects.ExistingTTTGame;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExistingTTTGameRowMapper implements RowMapper<ExistingTTTGame> {

    @Override
    public ExistingTTTGame mapRow(ResultSet rs, int rowNum) throws SQLException {
        ExistingTTTGame x = new ExistingTTTGame();
        x.setId(rs.getLong("id"));//this is the id of the game, not the login
        x.setCreated(rs.getString("Created"));
        x.setUsername(rs.getString("UserName"));
        x.setGameStatus(rs.getString("GameStatus"));

        return x;
    }
}
