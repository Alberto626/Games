package com.example.Ships.Login;

import com.example.Ships.Service.UserService;
import com.example.Ships.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/register")
public class RegisterController {
    @Autowired
    private UserService userService;
    @GetMapping()
    public String register(@ModelAttribute User user, Model model) {
        model.addAttribute(user);
        return "register";
    }
    @PostMapping()
    public String save(@Valid User user, BindingResult bindingResult)
    {
        if(userService.doesAccountExist(user.getUsername())) {//check for preexisting account name

            bindingResult.addError(new FieldError("user", "username", "Account already exists"));
        }
        if(bindingResult.hasErrors()) {//check for errors, annotation errors in User class
            return "register";//return and give errors
        }
        //register account
        userService.register(user);
        return "redirect:/login?successful";//successful, https://stackoverflow.com/questions/29560633/difference-between-redirect-and-view-rendering-in-spring-mvc
    }
}
