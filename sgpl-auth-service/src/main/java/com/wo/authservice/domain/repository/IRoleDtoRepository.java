package com.wo.authservice.domain.repository;

import com.wo.authservice.domain.dto.details.RoleDetailsDto;
import com.wo.authservice.domain.request.RegisterRoleRequest;
import com.wo.authservice.domain.response.ActiveRoleResponse;
import com.wo.authservice.domain.util.ICrudReposAssistant;

import java.util.List;

public interface IRoleDtoRepository extends ICrudReposAssistant<RoleDetailsDto, RegisterRoleRequest, Long> {

    List<ActiveRoleResponse> findAllByActiveTrue();

}
