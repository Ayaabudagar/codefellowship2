package com.example.demo.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;


public class ApplicationUser implements UserDetails  {
    private DBUser dbUser;

    public ApplicationUser(DBUser dbUser) {
        this.dbUser = dbUser;
    }


    public  ApplicationUser (){

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    @Override
    public String getPassword() {
        return dbUser.getPassword();
    }



    public String getUsername() {
        return dbUser.getUsername();
    }

    public Integer getId() {
        return dbUser.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}





