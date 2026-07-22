package com.eustache.pos_system.Repositories;

import com.eustache.pos_system.Entities.User;
import com.eustache.pos_system.Helpers.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    List<User> findByRole(RoleEnum role);
}
