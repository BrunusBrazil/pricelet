package com.wealth.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.wealth.common.account.Account;
import com.wealth.resource.AccountResource;

public class AccountAssembler  extends ResourceAssemblerSupport<Account, AccountResource>{ 
	
	public AccountAssembler() {
		super(Account.class, AccountResource.class);
	}

	@Override
	public AccountResource toResource(Account account) {
		AccountResource ac = new AccountResource();
		ac.setCodigo(account.getCodigo());
		ac.setDescription(account.getDescription());
		ac.setSubgroup(account.getSubgroup());
		return ac;
	}


}
