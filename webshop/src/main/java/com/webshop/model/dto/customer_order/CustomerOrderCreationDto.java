package com.webshop.model.dto.customer_order;

import java.sql.Date;

import com.webshop.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerOrderCreationDto {

	private Date dateOfCreation;
	private Float totalBill;
	private Boolean payed;
	private Date timeOfPayment;
	private Boolean sent;
	private Date timeWhenSent;
	
	private User customer;
	private User employee;
}
