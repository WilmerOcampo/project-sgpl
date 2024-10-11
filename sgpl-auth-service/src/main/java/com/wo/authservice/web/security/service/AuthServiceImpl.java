package com.wo.authservice.web.security.service;

import com.wo.authservice.web.security.CustomUserDetails;
import com.wo.authservice.web.security.jwt.JwtUtils;
import com.wo.authservice.web.security.payload.LoginRequest;
import com.wo.authservice.web.security.payload.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    @Override
    public LoginResponse login(LoginRequest userRequest) {
        String username = userRequest.username();
        String password = userRequest.password();

        Authentication authentication = authenticateUser(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtils.createToken(authentication);
        if (authentication.getPrincipal() instanceof CustomUserDetails customUser) {
            Long userId = customUser.getId();
            Set<String> roles = customUser.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toSet());
            return new LoginResponse(userId, roles, "User logged successfully", token, true);
        }
        throw new IllegalStateException("Unexpected authentication principal type");
    }

    private Authentication authenticateUser(String username, String password) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        if (userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

}
