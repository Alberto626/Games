package com.example.Ships.Login;

import com.example.Ships.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/register")
public class RegisterController {
    @GetMapping()
    public String register(@ModelAttribute User user, Model model) {
        model.addAttribute(user);
        return "register";
    }
    @PostMapping() //use responsebody to test out the register
    public String Post(@Valid User user, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()) {
            return "register";//return and give errors
        }
        // validate for duplication for username
        //add to database
        //successful
        return "login"; // successful
    }
}
