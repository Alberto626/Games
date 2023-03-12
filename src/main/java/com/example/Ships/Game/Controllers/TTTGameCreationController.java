package com.example.Ships.Game.Controllers;

import com.example.Ships.Game.Entities.Move;
import com.example.Ships.Game.Entities.TTTGame;
import com.example.Ships.Repo.SimpleRepo2;
import com.example.Ships.Service.UserPrincipal.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/createTTT")
public class TTTGameCreationController {
    @Autowired
    private SimpleRepo2 repo;
    @GetMapping
    public String createTTT(@ModelAttribute TTTGame TTTGame, Model model) {
        List<String> types = new ArrayList<>();
        List<String> pieces = new ArrayList<>();
        types.add("BOT");
        types.add("PVP");
        //TODO add PVP later
        pieces.add("X");
        pieces.add("O");
        model.addAttribute("types", types);
        model.addAttribute("pieces", pieces);
        model.addAttribute("TTTGame", TTTGame);
        return "createTTT";
    }
    @PostMapping
    public String createGame(@Valid TTTGame tttGame) {
        MyUserPrincipal user = (MyUserPrincipal)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        tttGame.setFirstPlayerID(user.getID());
        if(tttGame.getGameType().equals("PVP")) {//prevent play without an extra player
            tttGame.setGameStatus("HOLD");

        }
        else {
            tttGame.setGameStatus("IN PROGRESS");

        }
        tttGame = repo.saveTTTGame(tttGame);
        System.out.println(tttGame.getGameType() + "  " + tttGame.getFirstPlayerPiece());
        if(tttGame.getFirstPlayerPiece() == 'O' && tttGame.getGameType().equals("BOT")) {//X goes first
            Move move = createBotMove(tttGame.getId());
            repo.saveBOTMove(move);
        }
        return "redirect:/TTT/" + tttGame.getId();
    }
    public Move createBotMove(long gameID) {//the player id for bot is zero in backend, but database its null.
        int col = (int)(Math.random()* 3) + 1;
        int row = (int)(Math.random()* 3) + 1;
        Move move = new Move();
        move.setGameID(gameID);
        move.setBoardColumn(col);
        move.setBoardRow(row);
        return move;
    }
}
