package com.webshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webshop.model.CustomerOrder;
import com.webshop.repository.CustomerOrderRepository;

@Service
public class CustomerOrderService {

	@Autowired
	private CustomerOrderRepository customerOrderRepo;
	
	
	public List<CustomerOrder> getAllCustomerOrders() {
		return customerOrderRepo.findAll();
	}
	
	public Optional<CustomerOrder> getCustomerById(String customerOrderId){
		return customerOrderRepo.findById(customerOrderId);
	}
	
	public CustomerOrder addCustomerOrder(CustomerOrder CustomerOrder) {
		return customerOrderRepo.save(CustomerOrder);
	}
	
	public void deleteById(String customerOrderId) {
		customerOrderRepo.deleteById(customerOrderId);
	}
	
	public boolean existsById(String customerOrderId) {
		if(getCustomerById(customerOrderId).isPresent()) {
			return true;
		} else
			return false;
	}
}
