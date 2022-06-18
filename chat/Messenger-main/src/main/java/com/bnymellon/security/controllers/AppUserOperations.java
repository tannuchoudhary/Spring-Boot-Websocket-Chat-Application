package com.bnymellon.security.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bnymellon.constants.APIRoutes;
import com.bnymellon.security.models.AppUser;
import com.bnymellon.security.models.Role;

public interface AppUserOperations {

	ResponseEntity<List<AppUser>> getUsers();
	
	ResponseEntity<AppUser> saveUser(AppUser user);

	ResponseEntity<Role> saveRole(Role role);

	ResponseEntity<?> addRoleToUser(RoleToUserForm form);

	ResponseEntity<AppUser> getUser(String username);

}