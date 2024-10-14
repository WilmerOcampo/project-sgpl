package com.wo.authservice.web.security.payload;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Set;

@JsonPropertyOrder({"userId", "roles", "message", "jwtToken", "isAuthenticated"})
public record LoginResponse(Long userId, Set<String> roles, String message,
                            String jwtToken,
                            Boolean isAuthenticated) {
}
