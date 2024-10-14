package com.wo.authservice.persistence.mapper;

import com.wo.authservice.domain.dto.details.UserDetailsDto;
import com.wo.authservice.domain.dto.register.RegisterUserDto;
import com.wo.authservice.persistence.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserMapper {

    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    UserDetailsDto toUserDto(User user);

    List<UserDetailsDto> toUsersDto(List<User> users);

    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User toUser(RegisterUserDto userDto);

}
