package com.webshop.model.dto.employee;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCreationDto {
	
	private String name;
	private String surname;
	private String adress;
	private String username;
	private String email;
	private String password;
	private String contactNumber;
	private String role;
}
