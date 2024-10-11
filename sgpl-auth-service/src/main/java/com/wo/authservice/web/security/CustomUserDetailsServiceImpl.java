package com.wo.authservice.web.security;

import com.wo.authservice.persistence.model.User;
import com.wo.authservice.persistence.service.IPersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final IPersistenceService persistenceService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = persistenceService.findByUsernameOrEmail(username, username)
                .orElseThrow(() -> new UsernameNotFoundException("User: " + username + " does not exist."));
        user.setRoles(persistenceService.getUserRoles(user.getId()));
        return new CustomUserDetails(user);
    }
}
