package com.example.Ships.Service;

import com.example.Ships.Repo.SimpleRepo2;

import com.example.Ships.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private SimpleRepo2 repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUserName(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }

        UserDetails user2 = org.springframework.security.core.userdetails.User.withUsername(user.getUsername()).password(user.getPassword()).authorities("USER").build();// create a wrapper class later, all users will be defaults, no admins
        return user2;
    }
}
