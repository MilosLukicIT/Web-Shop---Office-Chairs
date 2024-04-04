package com.webshop.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.model.Customer;
import com.webshop.model.dto.CustomerCreationDto;
import com.webshop.service.CustomerService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("customer")
@RequiredArgsConstructor
public class CustomerContoller {
	
	private final CustomerService customerService;
	
	private final ModelMapper mapper;
	
	@GetMapping
	public ResponseEntity<?> getAllCustomer() {
		return ResponseEntity.ok(customerService.getAllCustomers());
	}
	
	@GetMapping
	public ResponseEntity<?> getCustomerById(@PathVariable String customerId) {
		
		if(customerService.existsById(customerId)) {
			return ResponseEntity.ok(customerService.getCustomerById(customerId));
		}
		else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found");
		
	}
	
	@PostMapping
	public ResponseEntity<?> createCustomer(@RequestBody CustomerCreationDto customer) {
		Customer customerModel = mapper.map(customer, Customer.class) ;
		return ResponseEntity.ok(customerService.addCustomer(customerModel));
	}


}
