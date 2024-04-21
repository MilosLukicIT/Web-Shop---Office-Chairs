package com.webshop.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.webshop.model.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	public abstract User findByEmailContainingIgnoreCase(String email);

}
