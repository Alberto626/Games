package com.example.Ships.Service;
import com.example.Ships.Repo.SimpleRepo2;
import com.example.Ships.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService { //grab from my repository and to be used in my register controller for validation.
    private final SimpleRepo2 repo;
    @Autowired
    public UserService(SimpleRepo2 repo) {
        this.repo = repo;
    }
    public boolean doesAccountExist(String username) {
        return repo.doesUserNameExist(username);
    }
    public void register(User user) {//register account to database using JDBC template
        repo.saveUser(user);

    }

}
