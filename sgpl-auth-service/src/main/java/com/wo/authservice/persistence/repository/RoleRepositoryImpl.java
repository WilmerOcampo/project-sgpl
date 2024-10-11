package com.wo.authservice.persistence.repository;

import com.wo.authservice.domain.dto.details.RoleDetailsDto;
import com.wo.authservice.domain.repository.IRoleDtoRepository;
import com.wo.authservice.domain.request.RegisterRoleRequest;
import com.wo.authservice.domain.response.ActiveRoleResponse;
import com.wo.authservice.persistence.mapper.IRoleMapper;
import com.wo.authservice.persistence.model.Role;
import com.wo.authservice.persistence.service.BaseAuditableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class RoleRepositoryImpl extends BaseAuditableRepository<Role, RoleDetailsDto> implements IRoleDtoRepository {

    private final IRoleRepository roleRepository;
    private final IRoleMapper roleMapper;

    @Autowired
    public RoleRepositoryImpl(IUserRepository userRepository, IRoleRepository roleRepository, IRoleMapper roleMapper) {
        super(userRepository);
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleDetailsDto> findAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(this::mapRoleToDetailsDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RoleDetailsDto> findById(Long id) {
        return roleRepository.findById(id).map(this::mapRoleToDetailsDto);
    }

    private RoleDetailsDto mapRoleToDetailsDto(Role role) {
        RoleDetailsDto details = roleMapper.toRoleDto(role);
        return mapUserToDetailsDto(role, details);
    }

    @Override
    @Transactional
    public void save(RegisterRoleRequest roleRequest) {
        roleRepository.save(roleMapper.toRole(roleRequest));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public List<ActiveRoleResponse> findAllByActiveTrue() {
        List<Role> roles = roleRepository.findAllByActiveTrue();
        return roleMapper.toActiveRoles(roles);
    }
}
