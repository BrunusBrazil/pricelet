package com.wealth.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wealth.common.account.Account;
import com.wealth.common.model.TransactionType;
import com.wealth.personal.PersonalTransaction;
import com.wealth.personal.PersonalTransactionService;

@Controller
public class PersonalTransactionController {

	@Autowired
	@Qualifier("personalTransactionService")
	private PersonalTransactionService personalTransactionService;

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String getHome() throws Exception{
		PersonalTransaction pt = new PersonalTransaction();
		TransactionType tt = new TransactionType();
		Account ac = new Account();
		pt.setDateTime(new Date(0));
		pt.setValue(1000D);
		pt.setAccount(ac);
		pt.setTypeTransaction(tt);
		personalTransactionService.gravar(pt);
		return "index";
	}
	
	
}
