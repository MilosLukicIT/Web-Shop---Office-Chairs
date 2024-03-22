package com.webshop.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.webshop.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
	
	

}
