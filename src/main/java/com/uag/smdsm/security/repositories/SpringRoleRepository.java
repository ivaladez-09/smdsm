package com.uag.smdsm.security.repositories;

import com.uag.smdsm.security.entities.SpringRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringRoleRepository extends JpaRepository<SpringRole, Long> {
    Optional<SpringRole> findByName(String name);
}
