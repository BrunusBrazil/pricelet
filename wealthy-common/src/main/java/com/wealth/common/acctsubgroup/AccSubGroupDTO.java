package com.wealth.common.acctsubgroup;

import com.wealth.common.accountgroup.AccountGroupDTO;

public class AccSubGroupDTO {

	private Integer id;
	private String description;
	private AccountGroupDTO account;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
