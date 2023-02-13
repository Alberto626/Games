package com.example.Ships.Test;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class testController {
    //this is MERELY A TEST
    @GetMapping
    public String here(@AuthenticationPrincipal User user, Model model) {

        System.out.println(user.getUsername());
        model.addAttribute(user.getUsername());
        return "test";
    }
}
