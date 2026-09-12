package com.nikhil.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserRequestDto {
	
	@NotBlank(message = "Name is Required")
	private String name;
	
	@NotBlank(message = "email is required")
	@Email(message = "Please provide valid email")
	private String email;
	
	private String phone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "UserRequestDto [name=" + name + ", email=" + email + ", phone=" + phone + "]";
	}
	
	

}
