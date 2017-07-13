package com.wealth.resource;

import org.springframework.hateoas.ResourceSupport;

import com.wealth.common.accountgroup.AccountGroupDTO;

public class AccSubGroupResource extends ResourceSupport {
	
	private String description;
	private AccountGroupDTO account;

	//getters and setters
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public AccountGroupDTO getAccount() {
		return account;
	}
	public void setAccount(AccountGroupDTO account) {
		this.account = account;
	}

}	

