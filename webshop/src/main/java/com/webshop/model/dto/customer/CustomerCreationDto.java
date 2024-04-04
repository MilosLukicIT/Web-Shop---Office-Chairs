package com.webshop.model.dto.customer;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCreationDto {
	
	private String name;
	private String surname;
	private String adress;
	private String username;
	private String email;
	private String password;
	private String contactNumber;
}
