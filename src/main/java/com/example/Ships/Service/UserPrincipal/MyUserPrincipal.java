package com.example.Ships.Service.UserPrincipal;

import com.example.Ships.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MyUserPrincipal implements UserDetails { //add more, if needed additional attributes from user class
    private User user;
    public MyUserPrincipal(User user) {
        this.user = user;
    }
    public long getID() {
        return user.getId();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    } //fix this later

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    } //default is true

    @Override
    public boolean isAccountNonLocked() {
        return true;
    } //default is true

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }//default is true

    @Override
    public boolean isEnabled() {
        return true;
    } //default is true
}
