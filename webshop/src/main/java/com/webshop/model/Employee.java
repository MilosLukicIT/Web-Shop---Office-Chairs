package com.webshop.model;

import java.util.List;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
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
public class Employee {
	
	@Id
	@UuidGenerator
	private String employeeId;
	private String name;
	private String surname;
	private String role;
	private String adress;
	private String username;
	private String email;
	private String password;
	private String contactNumber;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "employee", cascade =  CascadeType.REMOVE)
	List<CustomerOrder> customerOrders;

}
