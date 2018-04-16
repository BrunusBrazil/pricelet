package com.wealth.common.acctsubgroup;

import com.wealth.common.ModelDTO;
import com.wealth.common.accountgroup.AccountGroupDTO;

public class AccSubGroupDTO extends ModelDTO {

	private String description;
	private AccountGroupDTO account;
	

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
