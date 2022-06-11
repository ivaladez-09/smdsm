package com.uag.smdsm.security.repositories;

import com.uag.smdsm.security.entities.SpringUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringUserRepository extends JpaRepository<SpringUser, Long> {
    Optional<SpringUser> findByEmail(String email);
    Optional<SpringUser> findByUsernameOrEmail(String username, String email);
    Optional<SpringUser> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
