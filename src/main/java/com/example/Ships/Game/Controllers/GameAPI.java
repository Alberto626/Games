package com.example.Ships.Game.Controllers;

import com.example.Ships.Game.Entities.Move;
import com.example.Ships.Game.Entities.TTTGame;
import com.example.Ships.Repo.SimpleRepo2;
import com.example.Ships.Service.UserPrincipal.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/game", produces = "application/json")
public class GameAPI {
    @Autowired
    private SimpleRepo2 repo;

    @GetMapping("/TTTGameAPI") //this returns based on user id
    public List<TTTGame> gameboard()
    {
        MyUserPrincipal user = (MyUserPrincipal)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return repo.getGamesByPlayerID(user.getID());
    }
    @GetMapping("/Moves")
    public List<Move> gameMoves(@RequestParam(required = true, name = "id") String id) {
        int gameID = Integer.parseInt(id);//TODO fix this later for whole numbers and very large numbers
        return repo.getAllMovesByGameID(gameID);
    }
}
