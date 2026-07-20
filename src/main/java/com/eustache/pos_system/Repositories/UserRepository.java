package com.eustache.pos_system.Repositories;

import com.eustache.pos_system.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}