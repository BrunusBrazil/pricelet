package com.wealth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.wealth.common.account.Account;
import com.wealth.common.account.AccountDAO;
import com.wealth.common.account.AccountService;


@Service("accountServiceImpl")
public class AccountServiceImpl implements AccountService {

	@Autowired
	@Qualifier("accountDaoImpl")
	private AccountDAO dao;
	
	@Override
	public Account update(Account account) throws Exception {
		return dao.update(account);
	}

}
