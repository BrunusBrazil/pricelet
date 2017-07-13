package com.wealth.assembler;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import com.wealth.common.accountgroup.AccountGroupDTO;
import com.wealth.controller.AccountGroupController;
import com.wealth.resource.AccountResource;

public class AccountGroupAssembler  extends ResourceAssemblerSupport<AccountGroupDTO, AccountResource>{ 
	
	public AccountGroupAssembler() {
		super(AccountGroupDTO.class, AccountResource.class);
	}

	@Override
	public AccountResource toResource(AccountGroupDTO account) {
		AccountResource ac = new AccountResource();
		ac.setDescription(account.getDescription());
		Link link = linkTo(AccountGroupController.class).slash(account.getId()).withSelfRel();
 	    ac.add(link);
		return ac;
	}


}
