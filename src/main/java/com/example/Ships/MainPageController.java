package com.example.Ships;

import com.example.Ships.Game.APIObjects.ClosedTTTPVPMatch;
import com.example.Ships.Game.APIObjects.ExistingTTTGame;
import com.example.Ships.Game.Entities.TTTGame;
import com.example.Ships.Repo.SimpleRepo2;
import com.example.Ships.Service.UserPrincipal.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "/")
public class MainPageController {
    @Autowired
    private SimpleRepo2 repo;

    @GetMapping()
    public String main(@ModelAttribute ArrayList<ExistingTTTGame> openGames,
                       @ModelAttribute ArrayList<TTTGame> botGames,
                       @ModelAttribute ArrayList<ClosedTTTPVPMatch> closedPVPGames,
                       @ModelAttribute ArrayList<TTTGame> yourOpenGames,
                       Model model) {
        MyUserPrincipal user = (MyUserPrincipal)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        openGames = (ArrayList<ExistingTTTGame>) repo.getOpenPvPGames(user.getID());
        botGames = (ArrayList<TTTGame>) repo.getAllBOTGamesbyPlayerID(user.getID());
        closedPVPGames = (ArrayList<ClosedTTTPVPMatch>) repo.getClosedPVPGamesByPlayerID(user.getID());
        yourOpenGames = (ArrayList<TTTGame>) repo.getYourOpenPVPGamesByPlayerID(user.getID());
        model.addAttribute("openGames", openGames);
        model.addAttribute("botGames", botGames);
        model.addAttribute("closedPVPGames", closedPVPGames);
        model.addAttribute("yourOpenGames", yourOpenGames);
        //TODO add another attribute that allows the player to join their games created/join
        return "index";
    }
}
