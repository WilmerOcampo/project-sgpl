package com.wo.authservice.domain.service;

import com.wo.authservice.domain.dto.details.UserDetailsDto;
import com.wo.authservice.domain.dto.register.RegisterUserDto;
import com.wo.authservice.domain.response.OperationResponse;
import com.wo.authservice.domain.util.ICrudServiceAssistant;

public interface IUserService extends ICrudServiceAssistant<UserDetailsDto, RegisterUserDto, OperationResponse, Long> {
}
