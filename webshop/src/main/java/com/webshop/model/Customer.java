package com.webshop.model;

import java.util.List;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Customer{

	
	@Id
	@UuidGenerator
	private String customerId;
	@Column(length = 30, nullable = false, unique = false)
	private String name;
	@Column(length = 30, nullable = false, unique = false)
	private String surname;
	@Column(length = 30, nullable = false, unique = false)
	private String adress;
	@Column(length = 30, nullable = false, unique = true)
	private String username;
	@Column(length = 30, nullable = false, unique = true)
	private String email;
	@Column(length = 30, nullable = false, unique = false)
	private String password;
	@Column(length = 30, nullable = false, unique = false)
	private String contactNumber;
	
	@JsonIgnore
	@OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
	List<CustomerOrder> customerOrders;
}
