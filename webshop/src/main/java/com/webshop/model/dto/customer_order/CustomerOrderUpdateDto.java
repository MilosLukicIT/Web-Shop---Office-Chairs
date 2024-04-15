package com.webshop.model.dto.customer_order;

import java.sql.Date;

import com.webshop.model.Customer;
import com.webshop.model.Employee;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerOrderUpdateDto {

	private String orderId;
	private Date dateOfCreation;
	private Float totalBill;
	private Boolean payed;
	private Date timeOfPayment;
	private Boolean sent;
	private Date timeWhenSent;
	
	private Customer customer;
	private Employee employee;
}
