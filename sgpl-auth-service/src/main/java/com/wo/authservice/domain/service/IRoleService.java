package com.wo.authservice.domain.service;

import com.wo.authservice.domain.dto.details.RoleDetailsDto;
import com.wo.authservice.domain.request.RegisterRoleRequest;
import com.wo.authservice.domain.response.ActiveRoleResponse;
import com.wo.authservice.domain.response.OperationResponse;
import com.wo.authservice.domain.util.ICrudServiceAssistant;

import java.util.List;

public interface IRoleService extends ICrudServiceAssistant<RoleDetailsDto, RegisterRoleRequest, OperationResponse, Long> {

    List<ActiveRoleResponse> findAllByActiveTrue();

}
