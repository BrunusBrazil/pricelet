package com.wealth.service;

import org.springframework.stereotype.Service;

import com.wealth.personal.service.MoneyTransaction;

@Service("moneyTransactionService")
public class MoneyTransactionService implements MoneyTransaction{

	@Override
	public void gravar(MoneyTransaction moneyTransaction) throws Exception {
		System.out.print("Eu sou foda");
	}

}
