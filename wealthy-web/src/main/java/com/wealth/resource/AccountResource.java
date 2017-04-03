package com.wealth.resource;

import org.springframework.hateoas.ResourceSupport;

import com.wealth.common.account.Account;

public class AccountResource extends ResourceSupport {

	private String codigo;
	
	private String description;
	
	private Account subgroup;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Account getSubgroup() {
		return subgroup;
	}

	public void setSubgroup(Account subgroup) {
		this.subgroup = subgroup;
	}

}
