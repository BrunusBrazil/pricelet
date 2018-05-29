package com.wealth.controller;

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

import com.wealth.assembler.UserAssembler;
import com.wealth.resource.UserResource;
import com.wealth.service.EmailServiceImpl;
import com.wealthy.common.user.UserDTO;
import com.wealthy.common.user.UserService;

@RestController
@RequestMapping(value="User")
public class UserController {

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService service;
	
	@Autowired
	@Qualifier("emailServiceImpl")
	EmailServiceImpl emailService;
	
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ResponseEntity<List<UserResource>> searchAll() throws Exception{
		List<UserDTO> userDTO  = service.searchAll();
		UserAssembler xt = new UserAssembler();
		List<UserResource> ar = xt.toResources(userDTO);
		return ResponseEntity.ok(ar);
	}
	
	@RequestMapping(value="/", method = RequestMethod.POST)
	public ResponseEntity<UserResource> create(@RequestBody UserDTO x) throws Exception{
		UserDTO accountGroupDTO  = service.merge(x);
		UserAssembler aa = new UserAssembler();
		UserResource ar = aa.toResource(accountGroupDTO);
		return new ResponseEntity<UserResource>(ar, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public  ResponseEntity<List<UserResource>> delete(@PathVariable Integer id) throws Exception{
		List<UserDTO> accountGroupDTO  = service.delete(id);
		UserAssembler aa = new UserAssembler();
		List<UserResource> ar = aa.toResources(accountGroupDTO);
		return ResponseEntity.ok(ar);
	}

	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public  ResponseEntity<UserResource> update(@PathVariable Integer id, @RequestBody UserDTO x) throws Exception{
		UserDTO accountGroupDTO  = service.merge(x);
		UserAssembler aa = new UserAssembler();
		UserResource ar = aa.toResource(accountGroupDTO);
		return new ResponseEntity<UserResource>(ar, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value="/{email}", method = RequestMethod.GET)
	public  ResponseEntity<UserResource> searchByEmail(@PathVariable String email) throws Exception{
		UserDTO accountGroupDTO  = service.searchByEmail(email);
		UserAssembler aa = new UserAssembler();
		UserResource ar = aa.toResource(accountGroupDTO);
		return new ResponseEntity<UserResource>(ar, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value="/recover", method = RequestMethod.POST)
	public HttpStatus recoverEmail(@RequestBody UserDTO user) throws Exception{
		emailService.recoverPassword(user.getEmail());	
		return HttpStatus.ACCEPTED;
	}
	
	@RequestMapping(value="/resetPassword", method = RequestMethod.POST)
	public  ResponseEntity<UserResource> resetPassword(@RequestBody UserDTO user) throws Exception{
		UserDTO userDTO = null;
		userDTO = service.resetPassword(user);
		UserAssembler aa = new UserAssembler();
		UserResource ar = aa.toResource(userDTO);
		return new ResponseEntity<UserResource>(ar, HttpStatus.ACCEPTED);
	}
	
	
	
}
