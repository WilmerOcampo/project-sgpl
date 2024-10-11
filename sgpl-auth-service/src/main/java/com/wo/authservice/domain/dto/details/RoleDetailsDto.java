package com.wo.authservice.domain.dto.details;

import com.wo.authservice.domain.dto.AuditableInfoDto;
import com.wo.authservice.util.enums.ERole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDetailsDto extends AuditableInfoDto {

    private Long id;

    private ERole name;

    private boolean active;

}
