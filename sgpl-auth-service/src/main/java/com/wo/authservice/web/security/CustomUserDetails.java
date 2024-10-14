package com.wo.authservice.web.security;

import com.wo.authservice.persistence.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {
    private final Collection<? extends GrantedAuthority> authorities;
    private final String password;
    private final String username;

    @Getter
    private final Long id;
    @Getter
    private final String name;
    @Getter
    private final String lastName;

    public CustomUserDetails(User user) {
        this.authorities = authorities(user);
        this.password = user.getPassword();
        this.username = user.getUsername();

        this.id = user.getId();
        this.name = user.getName();
        this.lastName = user.getLastName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    private Collection<? extends GrantedAuthority> authorities(User user) {
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }
}
