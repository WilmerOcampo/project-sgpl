package com.wo.authservice.domain.dto.register;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class RegisterUserDto {

    private Long id;

    @Size(max = 8, message = "DNI must be at most 8 characters long")
    private String dni;

    @Size(max = 100, message = "Name must be at most 100 characters long")
    private String name;

    @Size(max = 100, message = "Last name must be at most 100 characters long")
    private String lastName;

    @Email(message = "Email should be valid")
    //@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z]{2,}$", message = "Email format is invalid")
    private String email;

    @Size(max = 30, message = "Username must be at most 30 characters long")
    private String username;

    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    private Set<Long> roleIds = new HashSet<>();

    private boolean active;

}
