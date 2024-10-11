package com.wo.authservice.persistence.mapper;

import com.wo.authservice.domain.dto.details.RoleDetailsDto;
import com.wo.authservice.domain.request.RegisterRoleRequest;
import com.wo.authservice.domain.response.ActiveRoleResponse;
import com.wo.authservice.persistence.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IRoleMapper {

    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    RoleDetailsDto toRoleDto(Role role);

    ActiveRoleResponse toActiveRoleDto(Role role);

    List<RoleDetailsDto> toRolesDto(List<Role> roles);

    List<ActiveRoleResponse> toActiveRoles(List<Role> roles);

    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Role toRole(RegisterRoleRequest registerRoleRequest);

}
