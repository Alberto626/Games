package com.example.Ships.Game.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/create_game")
public class CreateGameController {

    @PostMapping
    public String create(@RequestParam String game) {
        if(game.equals("TicTacToe")) {
            return "redirect:/createTTT";
        }
        return "redirect:/";//this will be battleships for a future feature
    }
}
