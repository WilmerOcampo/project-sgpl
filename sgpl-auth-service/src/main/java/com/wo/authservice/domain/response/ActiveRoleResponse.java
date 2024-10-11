package com.wo.authservice.domain.response;

import com.wo.authservice.util.enums.ERole;

public record ActiveRoleResponse(Long id, ERole name) {
}
