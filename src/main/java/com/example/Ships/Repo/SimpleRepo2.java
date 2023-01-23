package com.example.Ships.Repo;

import com.example.Ships.Repo.CustomRowMappers.UserRowMapper;
import com.example.Ships.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@Repository
public class SimpleRepo2 implements TutorialRepo{

    @Autowired
    private PasswordEncoder bCrypt;
    private final JdbcTemplate template;
    @Autowired
    public SimpleRepo2(JdbcTemplate template) {
        this.template = template;
    }
    @Override
    public int findById(long ID) {
        return 0;
    }

    @Override
    public Boolean saveUser(User user) {//use a prepared statement
        String password = bCrypt.encode(user.getPassword());
        user.setPassword(password);
        String query = "insert into Login(UserName, Password) values(?,?)";
        return template.execute(query, new PreparedStatementCallback<>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1,user.getUsername());
                ps.setString(2,user.getPassword());
                return ps.execute();
            }
        });
    }

    @Override
    public User findByUserName(String username) { //todo check if this somehow breaks my authentication if fails
        String sql = "Select * from login where UserName = ?";
        try {
            return template.queryForObject(sql, new Object[]{username}, new UserRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }

    }

    public boolean doesUserNameExist(String username) {
        try {
            template.queryForObject("Select UserName From login where UserName = ?", String.class, username); // this is a prepared statement
        }
        catch (EmptyResultDataAccessException e) {//error that the query above doesn't return anything
            return false;
        }
        return true;
    }
    public String toString() {
        return "Repo works";
    }
}
