package com.wo.authservice.persistence.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class User extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(length = 8, nullable = false, unique = true)
    private String dni;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Column(length = 254, nullable = false, unique = true)
    @Email(message = "Email should be valid")
    private String email;

    @Column(length = 30, nullable = false, unique = true)
    private String username;

    @Column(length = 60, nullable = false)
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    private boolean active = true;

    @Transient
    private Set<String> roles = new HashSet<>();

}
