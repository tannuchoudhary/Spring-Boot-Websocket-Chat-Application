package com.bnymellon.security.services;

import java.util.List;

import com.bnymellon.security.models.AppUser;
import com.bnymellon.security.models.Role;



public interface AppUserService {
	AppUser saveUser(AppUser user);
	Role saveRole(Role role);
	void addRoleToUser(String username, String role);
	AppUser getUser(String username);
	List<AppUser> getUsers();
}
