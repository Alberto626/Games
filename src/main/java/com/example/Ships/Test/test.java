package com.example.Ships.Test;

import com.example.Ships.Repo.AppConfig;
import com.example.Ships.Repo.SimpleRepo;
import com.example.Ships.Repo.TutorialRepo;
import com.example.Ships.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class test { //THIS IS TESTING Purposes
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        String password = "buckwild";//mysql database
        String username = "Albert";
        TutorialRepo repo = context.getBean(TutorialRepo.class);
        boolean result = repo.doesUserNameExist("buckwild");
        System.out.println(result);
        User x = new User();

    }
}
