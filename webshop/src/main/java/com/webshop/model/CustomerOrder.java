package com.webshop.model;

import java.sql.Date;
import java.util.List;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CustomerOrder {


	@Id
	@UuidGenerator
	private String orderId;
	private Date dateOfCreation;
	private Float totalBill;
	private Boolean payed;
	private Date timeOfPayment;
	private Boolean sent;
	private Date timeWhenSent;
	
	
	@ManyToOne
	@JoinColumn(name = "customer")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "employee")
	private Employee employee;
	
	@JsonIgnore
	@OneToMany(mappedBy = "customerOrder", cascade = CascadeType.REMOVE)
	List<CustomerOrderArticle> customerOrderArticle;
}