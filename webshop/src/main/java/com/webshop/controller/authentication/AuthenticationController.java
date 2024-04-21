package com.webshop.controller.authentication;


import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.model.User;
import com.webshop.model.login.ErrorResponse;
import com.webshop.model.login.LoginRequest;
import com.webshop.model.login.LoginResponse;
import com.webshop.service.UserService;
import com.webshop.utilSecurity.JwtUtil;

import lombok.RequiredArgsConstructor;



@CrossOrigin
@RestController
@RequestMapping("/auth/login")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;
	private final UserService service;
	private final ModelMapper mapper;
	
	
	@PostMapping
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
		
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
			User user = mapper.map(service.getUserByEmail(authentication.getName()), User.class);
			
			String token = jwtUtil.createToken(user);
			LoginResponse response = new LoginResponse(loginRequest.getEmail(), token);
			
			return ResponseEntity.ok(response);
		}catch (BadCredentialsException e) {
			ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST,"Invalid email or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
		catch (Exception e) {
			ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}
	
}
