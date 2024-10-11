package com.wo.authservice.persistence.repository;

import com.wo.authservice.persistence.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRoleRepository extends JpaRepository<Role, Long> {

    List<Role> findAllByActiveTrue();

}
