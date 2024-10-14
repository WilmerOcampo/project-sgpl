package com.wo.authservice.persistence.repository;

import com.wo.authservice.domain.dto.details.UserDetailsDto;
import com.wo.authservice.domain.dto.register.RegisterUserDto;
import com.wo.authservice.domain.repository.IUserDtoRepository;
import com.wo.authservice.persistence.mapper.IUserMapper;
import com.wo.authservice.persistence.model.User;
import com.wo.authservice.persistence.model.UserRole;
import com.wo.authservice.persistence.model.pk.UserRoleId;
import com.wo.authservice.persistence.service.BaseAuditableRepository;
import com.wo.authservice.persistence.service.IPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl extends BaseAuditableRepository<User, UserDetailsDto> implements IUserDtoRepository {

    private final IUserRepository userRepository;
    private final IUserRoleRepository userRoleRepository;
    private final IUserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final IPersistenceService persistenceService;

    @Autowired
    protected UserRepositoryImpl(IUserRepository userRepository, IUserRoleRepository userRoleRepository, IUserMapper userMapper1, PasswordEncoder passwordEncoder, IPersistenceService persistenceService) {
        super(userRepository);
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userMapper = userMapper1;
        this.passwordEncoder = passwordEncoder;
        this.persistenceService = persistenceService;
    }

    @Override
    public List<UserDetailsDto> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::mapUserToDetailsDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDetailsDto> findById(Long id) {
        return userRepository.findById(id).map(this::mapUserToDetailsDto);
    }

    private UserDetailsDto mapUserToDetailsDto(User user) {
        user.setRoles(persistenceService.getUserRoles(user.getId()));
        UserDetailsDto details = userMapper.toUserDto(user);
        return mapUserToDetailsDto(user, details);
    }

    @Override
    @Transactional
    public void save(RegisterUserDto userDto) {
        User user = userMapper.toUser(userDto);
        encodeAndSetPassword(user, userDto.getPassword());
        User savedUser = userRepository.save(user);
        if (userDto.getRoleIds() == null || userDto.getRoleIds().isEmpty()) {
            userDto.setRoleIds(new HashSet<>());
            userDto.getRoleIds().add(2L);
        }
        userDto.getRoleIds().forEach(roleId -> {
            UserRole userRole = new UserRole(new UserRoleId(savedUser.getId(), roleId));
            userRoleRepository.save(userRole);
        });
    }

    private void encodeAndSetPassword(User user, String rawPassword) {
        user.setPassword(passwordEncoder.encode(rawPassword));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

}
