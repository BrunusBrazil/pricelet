package com.wealth.resource;

import org.springframework.hateoas.ResourceSupport;

public class UserResource  extends ResourceSupport{

	private String fullName; 

	private String email;
	
	private String password;

	private String userName;
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String name) {
		this.fullName = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
