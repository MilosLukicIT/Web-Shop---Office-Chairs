package com.webshop.model.dto.employee;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeUpdateDto {
	
	private String employeeId;
	private String name;
	private String surname;
	private String adress;
	private String username;
	private String role;
	private String email;
	private String contactNumber;
}
