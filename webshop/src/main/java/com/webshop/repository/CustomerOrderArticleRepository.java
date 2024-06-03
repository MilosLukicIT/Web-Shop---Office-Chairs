package com.webshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webshop.model.CustomerOrder;
import com.webshop.model.CustomerOrderArticle;

public interface CustomerOrderArticleRepository extends JpaRepository<CustomerOrderArticle, String> {
	
	public abstract List<CustomerOrderArticle> findByCustomerOrderArticle(CustomerOrder customerOrderArticle);

}
