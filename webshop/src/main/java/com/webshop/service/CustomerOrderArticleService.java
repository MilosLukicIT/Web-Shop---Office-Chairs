package com.webshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webshop.model.CustomerOrderArticle;
import com.webshop.repository.CustomerOrderArticleRepository;


@Service
public class CustomerOrderArticleService {

	
	@Autowired
	private CustomerOrderArticleRepository customerOrderArticleRepo;
	
	
	public List<CustomerOrderArticle> getAllCustomerOrderArticles() {
		return customerOrderArticleRepo.findAll();
	}
	
	public Optional<CustomerOrderArticle> getcustomerOrderArticlerById(String customerOrderArticleId){
		return customerOrderArticleRepo.findById(customerOrderArticleId);
	}
	
	public CustomerOrderArticle addcustomerOrderArticle(CustomerOrderArticle customerOrderArticle) {
		return customerOrderArticleRepo.save(customerOrderArticle);
	}
	
	public void deleteById(String customerOrderArticleId) {
		customerOrderArticleRepo.deleteById(customerOrderArticleId);
	}
	
	public boolean existsById(String customerOrderArticleId) {
		if(getcustomerOrderArticlerById(customerOrderArticleId).isPresent()) {
			return true;
		} else
			return false;
	}
}