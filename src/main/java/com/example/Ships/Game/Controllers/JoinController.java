package com.example.Ships.Game.Controllers;

import com.example.Ships.Game.Entities.TTTGame;
import com.example.Ships.Repo.SimpleRepo2;
import com.example.Ships.Service.UserPrincipal.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping(value = "/join")
public class JoinController {
    @Autowired
    private SimpleRepo2 repo;
    @PostMapping()
    public String joinGame(@RequestParam long gameID) {//TODO fix this, no clue why its breaking
        if(isGameAvailable(gameID)) {
            MyUserPrincipal user = (MyUserPrincipal)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            repo.updateTTTGameWithNewPlayer(gameID, user.getID());
            System.out.println("join worked");
            return "redirect:/TTT/" + gameID;
        }
        //TODO add model message saying this doesn't work
        System.out.println("join didn't work");
        return "redirect:/";
    }
    public boolean isGameAvailable(long gameID) {//TODO check if this works if the game cannot be found
        TTTGame tttGame = repo.findTTTGameByID(gameID);
        MyUserPrincipal user = (MyUserPrincipal)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if(tttGame.getSecondPlayerID() == 0
                && tttGame.getGameType().equals("PVP")
                && tttGame.getFirstPlayerID() != user.getID()) {
            return true;
        }
        return false;
    }
}
