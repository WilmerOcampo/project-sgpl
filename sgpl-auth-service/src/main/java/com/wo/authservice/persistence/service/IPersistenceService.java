package com.wo.authservice.persistence.service;

import com.wo.authservice.persistence.model.User;

import java.util.Optional;
import java.util.Set;

public interface IPersistenceService {

    Optional<User> findByUsernameOrEmail(String username, String email);

    Set<String> getUserRoles(Long userId);

}
