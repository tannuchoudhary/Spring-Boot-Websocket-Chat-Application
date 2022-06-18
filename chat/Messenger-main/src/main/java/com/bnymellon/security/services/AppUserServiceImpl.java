package com.bnymellon.security.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bnymellon.constants.CommonConstants;
import com.bnymellon.security.models.AppUser;
import com.bnymellon.security.models.Role;
import com.bnymellon.security.repositories.AppUserRepository;
import com.bnymellon.security.repositories.RoleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor /* Autowiring not required */
@Transactional
@Slf4j
public class AppUserServiceImpl implements AppUserService, UserDetailsService{
	
	private final AppUserRepository appUserRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	
	/* This came from UserDetailsService */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = appUserRepository.findByUsername(username);
		if (Objects.isNull(user)) {
			log.error(CommonConstants.USERNAME_NOT_FOUND_IN_THE_DATABASE, username);
			throw new UsernameNotFoundException(String.format(CommonConstants.USERNAME_NOT_FOUND_IN_THE_DATABASE, username));
		}else {
			log.info(CommonConstants.USERNAME_FOUND_IN_THE_DATABASE, username);
		}
		
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}

	@Override
	public AppUser saveUser(AppUser user) {
		log.info(CommonConstants.SAVING_NEW_USER_TO_DATABASE, user.getUsername());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return appUserRepository.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		log.info(CommonConstants.SAVING_NEW_ROLE_TO_DATABASE, role.getName());
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		AppUser user = appUserRepository.findByUsername(username);
		Role role = roleRepository.findByName(roleName);
		log.info(CommonConstants.ADDING_ROLE_TO_USER, roleName, username);
		user.getRoles().add(role);
	}

	@Override
	public AppUser getUser(String username) {
		log.info(CommonConstants.FETCHING_USER, username);
		return appUserRepository.findByUsername(username);
	}

	@Override
	public List<AppUser> getUsers() {
		/* Better use pagination here, as getting millions of users will cause
		 * heavy load to the backend server */
		log.info(CommonConstants.FETCHING_ALL_USERS);
		return appUserRepository.findAll();
	}


}
