package com.example.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SignupForm {

	@NotBlank
	public String name;
	@NotBlank
	public String password;
	@NotBlank
	@Email
	public String email;
}
