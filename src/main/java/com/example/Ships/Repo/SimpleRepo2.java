package com.example.Ships.Repo;

import com.example.Ships.Game.APIObjects.ClosedTTTPVPMatch;
import com.example.Ships.Game.APIObjects.ExistingTTTGame;
import com.example.Ships.Game.Entities.Move;
import com.example.Ships.Game.Entities.TTTGame;
import com.example.Ships.Repo.CustomRowMappers.*;
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
    public TTTGame findTTTGameByID(long gameID) { //TODO fix name to GET not find
        String sql = "Select * from ttt_game where id = ?";
        try{
            return template.queryForObject(sql, new Object[]{gameID}, new TTTRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }
    public List<TTTGame> getGamesByPlayerID(Long playerID) {//this can return an empty list
        String sql = "Select * from ttt_game where FirstPlayerID = ?";
        List<TTTGame> x = template.query(sql,new Object[]{playerID}, new TTTRowMapper());
        return x;
    }
    public void saveMove(Move move) {
        String sql = "insert into moves(PlayerID, GameID, BoardRow, BoardColumn) values(?,?,?,?)";
        template.execute(sql, new PreparedStatementCallback<>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setLong(1, move.getPlayerID());
                ps.setLong(2, move.getGameID());
                ps.setInt(3, move.getBoardRow());
                ps.setInt(4, move.getBoardColumn());
                return ps.execute();
            }

        });
    }
    public void saveBOTMove(Move move) {
        String sql = "insert into moves(GameID, BoardRow, BoardColumn) values(?,?,?)";
        template.execute(sql, new PreparedStatementCallback<>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setLong(1, move.getGameID());
                ps.setInt(2, move.getBoardRow());
                ps.setInt(3, move.getBoardColumn());
                return ps.execute();
            }

        });
    }
    public void conludeTTTGame(long tttGameID) {//TODO, update the game status of the game to conluded meaning no more moves and be implemented
        String sql = "Update ttt_game set GameStatus = 'CONCLUDED' where id = ?";
        template.update(sql, tttGameID);
    }
    //USED FOR DIFFERENT PURPOSE, isnt used anymore
    public List<Move> getAllMovesByGameID(long gameID) {
        String sql = "Select * from moves where GameID = ?";
        List<Move> x = template.query(sql, new Object[]{gameID}, new MoveRowMapper());
        return x;
    }
    public boolean doesMoveExist(int row, int col, long gameID) {
        try {
            String sql = "Select * from moves where BoardRow = ? AND BoardColumn = ? AND id = ?";
            template.queryForObject(sql, new Object[]{row, col, gameID}, new MoveRowMapper()); //this is a prepared statement
        }
        catch (EmptyResultDataAccessException e) {//if query doesn't return anything
            return false;
        }
        return true;
    }
    public void updateTTTGameWithNewPlayer(long gameID, long playerID) {//TODO add second player
        String sql = "Update ttt_game set SecondPlayerID = ?, GameStatus = 'IN PROGRESS' where id = ?";
        template.update(sql, playerID, gameID);
    }
    public List<ExistingTTTGame> getOpenPvPGames(long playerID) {//exclude your own
        String sql = "select login.UserName, ttt_game.id, ttt_game.Created, ttt_game.GameStatus " +
                "from login inner join ttt_game " +
                "on login.userid = ttt_game.FirstPlayerID where GameStatus = 'HOLD' and " +
                "GameType = 'PVP' AND SecondPlayerID is null and FirstPlayerID != ?";
        return template.query(sql, new Object[]{playerID}, new ExistingTTTGameRowMapper());

    }
    public List<TTTGame> getAllBOTGamesbyPlayerID(long playerID) {
        String sql = "select * from ttt_game where FirstPlayerID = ? AND GameType = 'BOT';";

        return template.query(sql, new Object[]{playerID}, new TTTRowMapper());
    }
    public List<ClosedTTTPVPMatch> getClosedPVPGamesByPlayerID(long playerID) {
        String sql = "select  t.id,\n" +
                "\t\tt.Created,\n" +
                "        t.FirstPlayerPiece,\n" +
                "\t\tl1.UserName as FirstPlayer,\n" +
                "\t\tl2.UserName as SecondPlayer,\n" +
                "        t.GameStatus\n" +
                "from ttt_game as t\n" +
                "inner join login as l1\n" +
                "on\n" +
                "t.FirstPlayerID = l1.userid\n" +
                "inner join login as l2 \n" +
                "on \n" +
                "t.SecondPlayerID = l2.Userid\n" +
                "where t.GameType = 'PVP' and (t.FirstPlayerID = ? OR t.SecondPlayerID = ?);\n";

        return template.query(sql, new Object[]{playerID, playerID}, new ClosedTTTPVPMatchRowMapper());
    }
    public List<TTTGame> getYourOpenPVPGamesByPlayerID(long playerID) {
        String sql = "SELECT * FROM ttt_game\n" +
                "where SecondPlayerID is Null and GameType = \"PVP\" AND FirstPlayerID = ?;";

        return template.query(sql, new Object[]{playerID}, new TTTRowMapper());
    }

    public String toString() {
        return "Repo works";
    }
}