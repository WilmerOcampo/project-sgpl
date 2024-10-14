package com.wo.authservice.persistence.repository;

import com.wo.authservice.persistence.model.UserRole;
import com.wo.authservice.persistence.model.pk.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRoleRepository extends JpaRepository<UserRole, UserRoleId> {

    List<UserRole> findByUserRoleId_UserId(Long userId);

}
