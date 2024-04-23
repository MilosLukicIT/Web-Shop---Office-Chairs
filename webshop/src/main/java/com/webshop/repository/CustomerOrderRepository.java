package com.webshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webshop.model.CustomerOrder;
import com.webshop.model.User;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, String> {

	List<CustomerOrder> findByCustomer(User customer);
}
