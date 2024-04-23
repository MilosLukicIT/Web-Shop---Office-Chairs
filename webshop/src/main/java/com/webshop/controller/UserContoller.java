package com.webshop.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.model.User;
import com.webshop.model.dto.user.UserCreationDto;
import com.webshop.model.dto.user.UserUpdateDto;
import com.webshop.model.dto.user.UserViewDto;
import com.webshop.service.UserService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserContoller {
	
	private final UserService userService;
	private final PasswordEncoder encoder;
	private final ModelMapper mapper;
	
	
	
	@GetMapping
	public ResponseEntity<?> getAllUsers() {
		
		List<User> users = userService.getAllUsers();
		List<UserViewDto> userDto = users
				  .stream()
				  .map(user -> mapper.map(user, UserViewDto.class))
				  .collect(Collectors.toList());
		
		return ResponseEntity.ok(userDto);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getCustomerById(@PathVariable String userId) {
		
		if(userService.existsById(userId)) {
			
			UserViewDto userDto = mapper.map(userService.getUserById(userId), UserViewDto.class);
			return ResponseEntity.ok(userDto);
		}
		else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		
	}
	
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody UserCreationDto user) {
		if(userService.existsByEmail(user.getEmail())) {
			return ResponseEntity.status(HttpStatus.FOUND).body("User with this email already exist");
		}
		User userModel = mapper.map(user, User.class);
		userModel.setPassword(this.encoder.encode(userModel.getPassword()));
		userModel.setRole("CUSTOMER");
		UserViewDto createdCustomerDto = mapper.map(userService.addUser(userModel), UserViewDto.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomerDto);
	}
	
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@PostMapping("/employee")
	public ResponseEntity<?> createEmployee(@RequestBody UserCreationDto user) {
		User userModel = mapper.map(user, User.class);
		userModel.setPassword(this.encoder.encode(userModel.getPassword()));
		UserViewDto createdCustomerDto = mapper.map(userService.addUser(userModel), UserViewDto.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomerDto);
	}
	
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable String userId) {
		
		if(userService.existsById(userId)) {
			userService.deleteById(userId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User has been deleted"); 
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User doesn't exist");
		}
	}
	
	
	@PutMapping
	public ResponseEntity<?> updateUser(@RequestBody UserUpdateDto user){
		
		if (!userService.existsById(user.getUserId())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User doesn't exist");
		} else {
			
			this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			User updateUser = mapper.map(userService.getUserById(user.getUserId()), User.class);
//			mapper.map(user, updateUser);
			
			updateUser.setName(user.getName());
			updateUser.setAdress(user.getAdress());
			updateUser.setContactNumber(user.getContactNumber());
			updateUser.setEmail(user.getEmail());
			updateUser.setSurname(user.getSurname());
			updateUser.setUsername(user.getUsername());
			updateUser.setRole(user.getRole());
			
			//Mapirati na view DTOs
			userService.addUser(updateUser);
			
			return ResponseEntity.ok(updateUser);
		}
		
	}


}
