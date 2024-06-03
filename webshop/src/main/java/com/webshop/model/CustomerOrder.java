package com.webshop.model;

import java.sql.Date;
import java.util.List;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
	@Column(nullable = false, unique = false)
	private Date dateOfCreation;
	@Column(unique = false, columnDefinition = "NUMERIC")
	private Float totalBill;
	@Column(unique = false, nullable = true)
	private Boolean payed;
	@Column(unique = false, nullable = true)
	private Date timeOfPayment;
	@Column(unique = false, nullable = true)
	private Boolean sent;
	@Column(unique = false, nullable = true)
	private Date timeWhenSent;
	
	
	@ManyToOne
	@JoinColumn(name = "customerId")
	private User customer;
	
	@ManyToOne
	@JoinColumn(name = "employeeId")
	private User employee;
	
	@JsonIgnore
	@OneToMany(mappedBy = "customerOrderArticle", cascade = CascadeType.REMOVE)
	List<CustomerOrderArticle> customerOrderArticle;
}
