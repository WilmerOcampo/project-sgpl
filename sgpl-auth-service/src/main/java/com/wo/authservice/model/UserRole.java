package com.wo.authservice.model;

import com.wo.authservice.model.pk.UserRoleId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_role")
@Getter
@Setter
public class UserRole extends Auditable {

    @EmbeddedId
    private UserRoleId userRoleId;

}
