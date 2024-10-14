package com.wo.authservice.persistence.service;

import com.wo.authservice.persistence.model.Role;
import com.wo.authservice.persistence.model.User;
import com.wo.authservice.persistence.model.UserRole;
import com.wo.authservice.persistence.repository.IRoleRepository;
import com.wo.authservice.persistence.repository.IUserRepository;
import com.wo.authservice.persistence.repository.IUserRoleRepository;
import com.wo.authservice.util.enums.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersistenceServiceImpl implements IPersistenceService {

    private final IUserRoleRepository userRoleRepository;
    private final IRoleRepository roleRepository;
    private final IUserRepository userRepository;

    @Override
    public Set<String> getUserRoles(Long userId) {
        List<UserRole> userRoles = userRoleRepository.findByUserRoleId_UserId(userId);
        return userRoles.stream()
                .map(ur -> ur.getUserRoleId().getRoleId())
                .map(roleId -> roleRepository.findById(roleId)
                        .map(Role::getName)
                        .map(ERole::name)
                        .orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<User> findByUsernameOrEmail(String username, String email) {
        return userRepository.findByUsernameOrEmail(username, email);
    }

}
