package com.webshop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.webshop.model.User;
import com.webshop.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
	
	private final UserRepository userRepo;
	
	
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}
	
	public Optional<User> getUserById(String userId){
		return userRepo.findById(userId);
	}
	
	public User addUser(User user) {
		return userRepo.save(user);
	}
	
	public void deleteById(String userId) {
		userRepo.deleteById(userId);
	}
	
	public User getUserByEmail(String email){
		
		return userRepo.findByEmailContainingIgnoreCase(email);
	}
	
	public boolean existsById(String userId) {
		if(getUserById(userId).isPresent()) {
			return true;
		} else
			return false;
	}
	
	public boolean existsByEmail(String email) {
		if(getUserByEmail(email) != null) {
			return true;
		} else
			return false;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = getUserByEmail(email);
		UserDetails userDetails = 
				org.springframework.security.core.userdetails.User.builder()
				.username(user.getEmail())
				.password(user.getPassword())
				.roles(user.getRole())
				.authorities("ROLE_" + user.getRole())
				.build();
		
		return userDetails;
	}
	
	
	
	
	
	

}
