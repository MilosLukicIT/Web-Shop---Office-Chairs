package com.webshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webshop.model.Customer;
import com.webshop.repository.CustomerRepository;


@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	
	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}
	
	public Optional<Customer> getCustomerById(String customerId){
		return customerRepo.findById(customerId);
	}
	
	public Customer addCustomer(Customer customer) {
		return customerRepo.save(customer);
	}
	
	public void deleteById(String customerId) {
		customerRepo.deleteById(customerId);
	}
	
	public boolean existsById(String customerId) {
		if(getCustomerById(customerId).isPresent()) {
			return true;
		} else
			return false;
	}
	
	
	
	
	
	

}
