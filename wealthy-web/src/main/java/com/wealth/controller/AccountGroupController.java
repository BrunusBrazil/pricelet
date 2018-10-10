package com.wealth.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wealth.assembler.AccountGroupAssembler;
import com.wealth.common.accountgroup.AccountGroupDTO;
import com.wealth.common.accountgroup.AccountGroupService;
import com.wealth.resource.AccountResource;

@RestController
@RequestMapping(value="Account")
public class AccountGroupController extends AbstractController{
	
	@Autowired
	@Qualifier("accountGroupServiceImpl")
	private AccountGroupService service;
	
	@RequestMapping(value="/", method = RequestMethod.POST)
	public ResponseEntity<AccountResource> create(@RequestBody AccountGroupDTO account, Principal principal) throws Exception{
		AccountGroupDTO accountGroupDTO  = service.merge((AccountGroupDTO) setPrincipal(principal, account));
		AccountGroupAssembler aa = new AccountGroupAssembler();
		AccountResource ar = aa.toResource(accountGroupDTO);
		return new ResponseEntity<AccountResource>(ar, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ResponseEntity<List<AccountResource>> searchAll(Principal principal) throws Exception{
		List<AccountGroupDTO> accountGroupDTO  = service.searchAll((AccountGroupDTO) setPrincipal(principal, new AccountGroupDTO()));
		AccountGroupAssembler aa = new AccountGroupAssembler();
		List<AccountResource> ar = aa.toResources(accountGroupDTO);
		return ResponseEntity.ok(ar);
	}

	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public  ResponseEntity<List<AccountResource>> delete(@PathVariable Integer id, Principal principal ) throws Exception{
		List<AccountGroupDTO> accountGroupDTO  =
				service.delete((AccountGroupDTO) setPrincipal(principal, new AccountGroupDTO(), id));
		AccountGroupAssembler aa = new AccountGroupAssembler();
		List<AccountResource> ar = aa.toResources(accountGroupDTO);
		return ResponseEntity.ok(ar);
	}

	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public  ResponseEntity<AccountResource> update(@PathVariable Integer id,
			@RequestBody AccountGroupDTO account, Principal principal) throws Exception{
		AccountGroupDTO accountGroupDTO  = 
				service.merge((AccountGroupDTO) setPrincipal(principal, account, id));
		AccountGroupAssembler aa = new AccountGroupAssembler();
		AccountResource ar = aa.toResource(accountGroupDTO);
		return new ResponseEntity<AccountResource>(ar, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public  ResponseEntity<AccountResource> searchById(@PathVariable Integer id, Principal principal) throws Exception{
		AccountGroupDTO accountGroupDTO  =
				service.searchById((AccountGroupDTO) setPrincipal(principal, new AccountGroupDTO(), id));
		AccountGroupAssembler aa = new AccountGroupAssembler();
		AccountResource ar = aa.toResource(accountGroupDTO);
		return new ResponseEntity<AccountResource>(ar, HttpStatus.ACCEPTED);
	}
	
}
