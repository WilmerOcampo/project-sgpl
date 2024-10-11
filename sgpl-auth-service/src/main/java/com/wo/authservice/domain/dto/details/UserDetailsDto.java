package com.wo.authservice.domain.dto.details;

import com.wo.authservice.domain.dto.AuditableInfoDto;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserDetailsDto extends AuditableInfoDto {

    private Long id;

    private String dni;

    private String name;

    private String lastName;

    private String email;

    private String username;

    private Set<String> roles = new HashSet<>();

    private boolean active;

}
