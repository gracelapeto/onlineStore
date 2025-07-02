package com.example.onlineStore.security;

import com.example.onlineStore.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private String username;
    private String password;
    private Boolean active;
    private List<GrantedAuthority> authorities;

    public UserDetailsImpl(User user) {
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.active = user.getActive();
            this.authorities=List.of(new SimpleGrantedAuthority(user.getRole().getName()));
        }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return username;
    }

    @Override
    public String getUsername() {
        return password;
    }
    @Override
    public boolean isEnabled() {
        return active;
    }

}

