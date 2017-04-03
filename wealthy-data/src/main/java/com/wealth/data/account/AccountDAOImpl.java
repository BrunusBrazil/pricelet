package com.wealth.data.account;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.wealth.common.account.Account;
import com.wealth.common.account.AccountDAO;
import com.wealth.data.GenericDaoImpl;

@Transactional
@Component("accountDaoImpl")
public class AccountDAOImpl extends GenericDaoImpl implements  AccountDAO{

	@Override
	public Account update(Account account) throws Exception {
		AccountDTO adto = new AccountDTO();
		adto.setId(account.getId());
		adto.setCodigo(account.getCodigo());
		adto.setDescription(account.getDescription());
		adto.setSubgroup(null);
		super.alterar(adto);
		
		account.setId(adto.getId());
		return account;
	}

}
