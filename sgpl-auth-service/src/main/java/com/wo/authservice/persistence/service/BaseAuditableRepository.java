package com.wo.authservice.persistence.service;

import com.wo.authservice.domain.dto.IAuditableDto;
import com.wo.authservice.persistence.model.IAuditableEntity;
import com.wo.authservice.persistence.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BaseAuditableRepository<T extends IAuditableEntity, D extends IAuditableDto> {

    protected final IUserRepository userRepository;

    protected String userAuditor(Long id) {
        if (id == null) return null;
        return userRepository.findById(id)
                .map(u -> u.getName() + " " + u.getLastName())
                .orElse("User not found");
    }

    protected D mapUserToDetailsDto(T entity, D dto) {
        dto.setCreatedBy(userAuditor(entity.getCreatedBy()));
        dto.setUpdatedBy(userAuditor(entity.getUpdatedBy()));
        return dto;
    }

}
