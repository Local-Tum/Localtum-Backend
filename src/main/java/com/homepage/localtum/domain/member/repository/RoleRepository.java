package com.homepage.localtum.domain.member.repository;

import com.homepage.localtum.domain.member.entity.Role;
import com.homepage.localtum.domain.member.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleName roleName);
}