package com.example.Ships.Repo.CustomRowMappers;


import com.example.Ships.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("Userid"));
        user.setUsername(rs.getString("UserName"));
        user.setPassword(rs.getString("Password"));
        return user;
    }
}
