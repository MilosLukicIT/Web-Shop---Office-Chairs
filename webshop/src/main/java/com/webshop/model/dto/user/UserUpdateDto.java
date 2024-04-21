package com.webshop.model.dto.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {
	
	private String userId;
	private String name;
	private String surname;
	private String adress;
	private String username;
	private String role;
	private String email;
	private String password;
	private String contactNumber;
}
