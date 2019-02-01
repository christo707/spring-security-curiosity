package com.christo.security.springsecuritycuriosity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.christo.security.springsecuritycuriosity.model.Role;
import com.christo.security.springsecuritycuriosity.model.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}