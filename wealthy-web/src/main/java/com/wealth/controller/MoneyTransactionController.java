package com.wealth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wealth.personal.service.MoneyTransaction;

@Controller
public class MoneyTransactionController {

	@Autowired
	@Qualifier("moneyTransactionService")
	private MoneyTransaction moneyTransactionService;

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String getHome() throws Exception{
		moneyTransactionService.gravar(null);
		return "index";
	}
	
	
}
