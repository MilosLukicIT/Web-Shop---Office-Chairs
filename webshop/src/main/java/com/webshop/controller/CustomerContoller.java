package com.webshop.controller;

import java.util.List;
import java.util.stream.Collectors;

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
import com.webshop.model.dto.customer.CustomerCreationDto;
import com.webshop.model.dto.customer.CustomerViewDto;
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
		
		List<Customer> customers = customerService.getAllCustomers();
		List<CustomerViewDto> customerDto = customers
				  .stream()
				  .map(customer -> mapper.map(customer, CustomerViewDto.class))
				  .collect(Collectors.toList());
		
		return ResponseEntity.ok(customerDto);
	}
	
	@GetMapping
	public ResponseEntity<?> getCustomerById(@PathVariable String customerId) {
		
		if(customerService.existsById(customerId)) {
			
			CustomerViewDto customerDto = mapper.map(customerService.getCustomerById(customerId), CustomerViewDto.class);
			return ResponseEntity.ok(customerDto);
		}
		else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found");
		
	}
	
	@PostMapping
	public ResponseEntity<?> createCustomer(@RequestBody CustomerCreationDto customer) {
		Customer customerModel = mapper.map(customer, Customer.class);
		CustomerViewDto createdCustomerDto = mapper.map(customerService.addCustomer(customerModel), CustomerViewDto.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomerDto);
	}


}
