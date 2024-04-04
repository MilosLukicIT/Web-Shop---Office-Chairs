package com.webshop.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webshop.model.CustomerOrder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

public class CustomerCreationDto {
	
	private String name;
	private String surname;
	private String adress;
	private String username;
	private String email;
	private String password;
	private String contactNumber;
	
	@JsonIgnore
	@OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
	List<CustomerOrder> customerOrders;
}
