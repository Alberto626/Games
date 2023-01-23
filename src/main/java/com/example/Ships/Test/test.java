package com.example.Ships.Test;

import com.example.Ships.Repo.AppConfig;
import com.example.Ships.Repo.SimpleRepo2;
import com.example.Ships.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class test { //THIS IS TESTING Purposes
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        String password = "buckwild";//mysql database
        String username = "Albert";
        SimpleRepo2 repo = context.getBean(SimpleRepo2.class);
        User user = repo.findByUserName("hello");
        if(user == null) {
            System.out.println("user is null");
        }
        else {
            System.out.println(user);
        }

    }
}
