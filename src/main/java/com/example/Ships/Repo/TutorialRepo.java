package com.example.Ships.Repo;

import com.example.Ships.User;

public interface TutorialRepo { //add more if necessary
    int saveUser(User user);
    int findById(long ID);
    User findByUserName(String username);
    boolean doesUserNameExist(String user);

}
