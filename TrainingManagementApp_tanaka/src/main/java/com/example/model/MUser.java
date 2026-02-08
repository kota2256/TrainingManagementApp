package com.example.model;

import lombok.Data;

@Data
public class MUser {

	private int id;
	private int roleCode;
	private String name;
	private String password;
	private String email;
}
