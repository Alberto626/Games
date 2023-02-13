package com.example.Ships.Service;

import com.example.Ships.Repo.SimpleRepo2;

import com.example.Ships.Service.UserPrincipal.MyUserPrincipal;
import com.example.Ships.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class MyUserDetailsService implements UserDetailsService { //a way of grabbing a username to be used by spring security
    @Autowired
    private SimpleRepo2 repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUserName(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
        //UserDetails user2 = org.springframework.security.core.userdetails.User.withUsername(user.getUsername()).password(user.getPassword()).authorities("USER").build();
        return new MyUserPrincipal(user);
    }
}
