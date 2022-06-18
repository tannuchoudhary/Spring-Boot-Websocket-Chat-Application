package com.bnymellon.security.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bnymellon.constants.APIRoutes;
import com.bnymellon.constants.CommonConstants;
import com.bnymellon.security.models.AppUser;
import com.bnymellon.security.models.Role;
import com.bnymellon.security.services.AppUserService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(APIRoutes.API.API)
@RequiredArgsConstructor  /* Autowiring not required */
@CrossOrigin("http://localhost:3000")
public class AppUserControllerImpl implements AppUserOperations {
	private final AppUserService appUserService;
	
	@Override
	@GetMapping(value = APIRoutes.API.USERS)
	public ResponseEntity<List<AppUser>> getUsers(){
		return ResponseEntity.ok().body(appUserService.getUsers());
	}
	
	@Override
	@GetMapping(value = APIRoutes.API.USERS_USERNAME)
	public ResponseEntity<AppUser> getUser(@PathVariable String username){
		return ResponseEntity.ok().body(appUserService.getUser(username));
	}
	
	// When data is taken from Form, we need not write @RequestBody
	@Override
	@PostMapping(value = APIRoutes.API.USERS, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppUser> saveUser(AppUser user){
		AppUser savedUser = appUserService.saveUser(user);
		appUserService.addRoleToUser(savedUser.getName(), CommonConstants.ROLE_USER);
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(APIRoutes.API.API_USERS).toUriString());
		return ResponseEntity.created(uri).body(savedUser);
	}
	
	@Override
	@PostMapping(value = APIRoutes.API.ROLES)
	public ResponseEntity<Role> saveRole(@RequestBody Role role){
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(APIRoutes.API.API_ROLES).toUriString());
		return ResponseEntity.created(uri).body(appUserService.saveRole(role));
	}
	
	@Override
	@PostMapping(value = APIRoutes.API.ROLE_ADDTOUSER)
	public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){
		appUserService.addRoleToUser(form.getUsername(), form.getRoleName());
		return ResponseEntity.ok().build();
	}
}

@Data
class RoleToUserForm{
	private String username;
	private String roleName;
}
