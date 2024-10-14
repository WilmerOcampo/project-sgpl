package com.wo.authservice.domain.request;

import com.wo.authservice.util.enums.ERole;
import jakarta.validation.constraints.NotNull;

public record RegisterRoleRequest(Long id,
                                  @NotNull(message = "Role name cannot be null") ERole name,
                                  boolean active) {
}
