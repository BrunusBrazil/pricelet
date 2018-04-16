package com.wealth.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.wealth.common.ModelDTO;
import com.wealth.common.xtransaction.XTransactionDTO;
import com.wealthy.common.user.UserDTO;
import com.wealthy.common.user.UserService;

public abstract class AbstractController {
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	
	protected UserDTO getCurrentUser(Principal principal) throws Exception{
		UserDTO user =  userService.searchByUserName(principal.getName());
		return user;
	}
	
	protected ModelDTO setPrincipal(Principal principal, ModelDTO model) throws Exception{
		model.setUserId(getCurrentUser(principal).getId());
		return model;
	}
	
	protected ModelDTO setPrincipal(Principal principal, ModelDTO model , Integer id) throws Exception{
		model.setId(id);
		model.setUserId(getCurrentUser(principal).getId());
		return model;
	}



}
