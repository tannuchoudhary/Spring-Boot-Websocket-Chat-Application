package com.bnymellon.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bnymellon.security.models.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long>{
	AppUser findByUsername(String username);
}
