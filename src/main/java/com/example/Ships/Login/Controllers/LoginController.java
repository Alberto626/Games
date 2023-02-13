package com.example.Ships.Login.Controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping(path = "/login")
public class LoginController {
    @GetMapping(path= "")
    public String login() {//change this to work with spring security instead of redirect in controller
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";

        }
        return "redirect:/";
    }
}
