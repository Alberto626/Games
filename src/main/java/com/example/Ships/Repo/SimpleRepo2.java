package com.example.Ships.Repo;

import com.example.Ships.Game.TTTGame;
import com.example.Ships.Repo.CustomRowMappers.TTTRowMapper;
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
import java.util.List;


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
        String password = bCrypt.encode(user.getPassword()); //hash password
        user.setPassword(password);
        String query = "insert into login(UserName, Password) values(?,?)";
        return template.execute(query, new PreparedStatementCallback<>() {//used preparedstatement to prevent sql injection
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1,user.getUsername());
                ps.setString(2,user.getPassword());
                return ps.execute();
            }
        });
    }

    @Override
    public User findByUserName(String username) { //find a way to not use depreciated function
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
            template.queryForObject("Select UserName From login where UserName = ?", String.class, username); //this is a prepared statement
        }
        catch (EmptyResultDataAccessException e) {//if query doesn't return anything, find anything
            return false;
        }
        return true;
    }
    public TTTGame saveTTTGame(TTTGame tttGame) {
        String sql = "insert into ttt_game(FirstPlayerID, GameType, FirstPlayerPiece, GameStatus) values(?,?,?,?)";
        template.execute(sql, new PreparedStatementCallback<>() {//add to database
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setLong(1,tttGame.getFirstPlayerID());
                ps.setString(2,tttGame.getGameType());
                ps.setString(3, String.valueOf(tttGame.getFirstPlayerPiece()));
                ps.setString(4, tttGame.getGameStatus());
                return ps.execute();
            }
        });
        return returnTTTObject(tttGame);
    }
    private TTTGame returnTTTObject(TTTGame tttGame) {//this is made for the object to have represented the mysql database u just created
        String sql = "Select * from ttt_game where FirstPlayerID = ? order by id desc limit 1;";
        return template.queryForObject(sql, new Object[]{tttGame.getFirstPlayerID()}, new TTTRowMapper());
    }
    public TTTGame findTTTGameByID() {
        return null;
    }
    public List<TTTGame> getAllGames() {
        return null;
    }
    public List<TTTGame> getGamesByID() {
        return null;
    }
    public String toString() {
        return "Repo works";
    }
}