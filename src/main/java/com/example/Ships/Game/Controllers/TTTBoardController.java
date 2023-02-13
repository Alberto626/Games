package com.example.Ships.Game.Controllers;

import com.example.Ships.Game.TTTGame;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;

@Controller
@RequestMapping("TTT")
public class TTTBoardController {
    @GetMapping()
    public String gameBoard(@ModelAttribute TTTGame tttGame) {
        if(tttGame.getId() == 0) {
            System.out.println("hello world");
        }
        return "TTTGameBoard";
    }

}
