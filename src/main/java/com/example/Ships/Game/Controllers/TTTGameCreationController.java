package com.example.Ships.Game.Controllers;

import com.example.Ships.Game.TTTGame;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        pieces.add("X");
        pieces.add("O");
        model.addAttribute("types", types);
        model.addAttribute("pieces", pieces);
        model.addAttribute("TTTGame", TTTGame);
        return "createTTT";
    }
    @PostMapping
    public String createGame(@Valid TTTGame tttGame, RedirectAttributes redirectAttributes) {
        MyUserPrincipal user = (MyUserPrincipal)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        tttGame.setFirstPlayerID(user.getID());
        tttGame.setGameStatus("IN PROGRESS");//fix this to have enums instead of strings later
        tttGame = repo.saveTTTGame(tttGame);
        redirectAttributes.addFlashAttribute(tttGame);
        return "redirect:/TTT";
    }
}
