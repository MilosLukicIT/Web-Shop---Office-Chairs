package com.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webshop.model.CustomerOrder;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, String> {

}
