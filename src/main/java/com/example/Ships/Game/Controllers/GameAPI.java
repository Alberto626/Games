package com.example.Ships.Game.Controllers;

import com.example.Ships.Game.TTTGame;
import com.example.Ships.Repo.SimpleRepo2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameAPI {
    @Autowired
    private SimpleRepo2 repo;

    @GetMapping("/TTTGameAPI")
    public List<TTTGame> gameboard(@RequestParam(required = false, name = "id") String id)
    {
        if(id == null) {//return entire gameboard

        }
        return null;
    }
}
