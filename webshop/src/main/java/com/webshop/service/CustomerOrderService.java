package com.webshop.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.webshop.model.CustomerOrder;
import com.webshop.model.User;
import com.webshop.repository.CustomerOrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerOrderService {

	private final CustomerOrderRepository customerOrderRepo;
	
	
	public List<CustomerOrder> getAllCustomerOrders() {
		return customerOrderRepo.findAll();
	}
	
	public void updateCustomerOrder(String customerOrderId, String sessionId){
		Optional<CustomerOrder> customerOrderOptional = getCustomerOrderById(customerOrderId);
		if(customerOrderOptional.isPresent()) {
			CustomerOrder customerOrder = customerOrderOptional.get();
			customerOrder.setPaymentSessionId(sessionId);
			customerOrderRepo.save(customerOrder);
		}
	}
	
	public void updateCustomerOrderPayment(String sessionId){
		List<CustomerOrder> customerOrderOptional = customerOrderRepo.findByPaymentSessionId(sessionId);
		if(!customerOrderOptional.isEmpty()) {
			CustomerOrder customerOrder = customerOrderOptional.get(0);
			customerOrder.setPayed(true);
			Date d = Date.valueOf(LocalDate.now());
			customerOrder.setTimeOfPayment(d);
			customerOrderRepo.save(customerOrder);
		}
	}
	
	public Optional<CustomerOrder> getCustomerOrderById(String customerOrderId){
		return customerOrderRepo.findById(customerOrderId);
	}
	
	public CustomerOrder addCustomerOrder(CustomerOrder CustomerOrder) {
		return customerOrderRepo.save(CustomerOrder);
	}
	
	public void deleteById(String customerOrderId) {
		customerOrderRepo.deleteById(customerOrderId);
	}
	
	public boolean existsById(String customerOrderId) {
		if(getCustomerOrderById(customerOrderId).isPresent()) {
			return true;
		} else
			return false;
	}
	
	public List<CustomerOrder> getCustomerOrderByCustomer(User user) {
		
		return customerOrderRepo.findByCustomer(user);
	}
}
