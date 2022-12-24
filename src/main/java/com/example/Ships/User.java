package com.example.Ships;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
public class User {
    private long id;

    @NotEmpty(message = "Enter your UserName") //error message if not working
    @Length(min = 3, max = 15, message = "UserName must between 3 and 15 characters")
    private String username;
    @NotEmpty(message = "Enter your password")
    @Length(min = 3, message = "Passwords must be at least 3 characters")
    private String password; //password MUST BE HASHED
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return username + " and " + password;
    }
}
