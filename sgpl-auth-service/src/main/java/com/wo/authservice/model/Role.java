package com.wo.authservice.model;

import com.wo.authservice.model.enums.ERole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role extends Identifier {

    @Column(name = "name", nullable = false)
    @Enumerated(EnumType.STRING)
    private ERole name;

    @Column(name = "is_active")
    private boolean isActive;

}
