package com.bnymellon.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bnymellon.security.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByName(String name);
}
