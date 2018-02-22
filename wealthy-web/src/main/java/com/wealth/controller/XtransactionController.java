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

import com.wealth.assembler.XTransactionAssembler;
import com.wealth.common.xtransaction.XTransactionDTO;
import com.wealth.common.xtransaction.XTransactionService;
import com.wealth.resource.XTransactionResource;
import com.wealthy.common.user.UserDTO;
import com.wealthy.common.user.UserService;

@RestController
@RequestMapping(value="XTransaction")
public class XtransactionController {

	@Autowired
	@Qualifier("xTransactionServiceImpl")
	private XTransactionService service;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
		
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ResponseEntity<List<XTransactionResource>> searchAll(Principal principal) throws Exception{
		List<XTransactionDTO> accSubGroupDTO  = service.searchAll(getNewInstanceXTransaction(principal));
		XTransactionAssembler xt = new XTransactionAssembler();
		List<XTransactionResource> ar = xt.toResources(accSubGroupDTO);
		return ResponseEntity.ok(ar);
	}
	
	@RequestMapping(value="/", method = RequestMethod.POST)
	public ResponseEntity<XTransactionResource> create(@RequestBody XTransactionDTO x, Principal principal) throws Exception{
		x.setUserId(getCurrentUser(principal).getId());
		XTransactionDTO accountGroupDTO  = service.merge(x);
		XTransactionAssembler aa = new XTransactionAssembler();
		XTransactionResource ar = aa.toResource(accountGroupDTO);
		return new ResponseEntity<XTransactionResource>(ar, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public  ResponseEntity<List<XTransactionResource>> delete(@PathVariable Integer id, Principal principal) throws Exception{
		List<XTransactionDTO> accountGroupDTO  = service.delete(getNewInstanceXTransaction(principal,id));
		XTransactionAssembler aa = new XTransactionAssembler();
		List<XTransactionResource> ar = aa.toResources(accountGroupDTO);
		return ResponseEntity.ok(ar);
	}

	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public  ResponseEntity<XTransactionResource> update( @PathVariable Integer id, @RequestBody XTransactionDTO x, Principal principal) throws Exception{
		x.setUserId(getCurrentUser(principal).getId());
		XTransactionDTO accountGroupDTO  = service.merge(x);
		XTransactionAssembler aa = new XTransactionAssembler();
		XTransactionResource ar = aa.toResource(accountGroupDTO);
		return new ResponseEntity<XTransactionResource>(ar, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public  ResponseEntity<XTransactionResource> searchById(@PathVariable Integer id, Principal principal) throws Exception{
		XTransactionDTO accountGroupDTO  = service.searchById(getNewInstanceXTransaction(principal, id));
		XTransactionAssembler aa = new XTransactionAssembler();
		XTransactionResource ar = aa.toResource(accountGroupDTO);
		return new ResponseEntity<XTransactionResource>(ar, HttpStatus.ACCEPTED);
	}	


	private XTransactionDTO getNewInstanceXTransaction(Principal principal) throws Exception{
		XTransactionDTO transactionDTO = new XTransactionDTO();
		transactionDTO.setUserId(getCurrentUser(principal).getId());
		return transactionDTO;
	}

	
	private XTransactionDTO getNewInstanceXTransaction(Principal principal, Integer id) throws Exception{
		XTransactionDTO transactionDTO = new XTransactionDTO();
		transactionDTO.setId(id);
		transactionDTO.setUserId(getCurrentUser(principal).getId());
		return transactionDTO;
	}

	private UserDTO getCurrentUser(Principal principal) throws Exception{
		UserDTO user =  userService.searchByUserName(principal.getName());
		return user;
	}
}
