package com.wo.authservice.domain.repository;

import com.wo.authservice.domain.dto.details.UserDetailsDto;
import com.wo.authservice.domain.dto.register.RegisterUserDto;
import com.wo.authservice.domain.util.ICrudReposAssistant;

public interface IUserDtoRepository extends ICrudReposAssistant<UserDetailsDto, RegisterUserDto, Long> {
}
