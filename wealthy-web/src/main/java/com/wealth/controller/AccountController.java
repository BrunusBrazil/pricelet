package com.wealth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wealth.assembler.AccountAssembler;
import com.wealth.common.account.Account;
import com.wealth.common.account.AccountService;
import com.wealth.resource.AccountResource;

@RestController
@RequestMapping(value="account")
public class AccountController {
	
	@Autowired
	@Qualifier("accountServiceImpl")
	private AccountService service;
	
	@RequestMapping(value="/", method = RequestMethod.POST)
	public ResponseEntity<AccountResource> create(@RequestBody Account account) throws Exception{
		service.update(account);
		AccountAssembler aa = new AccountAssembler();
		AccountResource ar = aa.toResource(account);
		return new ResponseEntity<AccountResource>(ar, HttpStatus.CREATED);
	}
	
	
}
