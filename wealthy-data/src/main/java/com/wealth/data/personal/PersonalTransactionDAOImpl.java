package com.wealth.data.personal;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.wealth.data.GenericDaoImpl;
import com.wealth.data.account.AccountDTO;
import com.wealth.personal.PersonalTransacionDAO;
import com.wealth.personal.PersonalTransaction;

@Component("personalTransactionDAOImpl")
public class PersonalTransactionDAOImpl extends GenericDaoImpl implements PersonalTransacionDAO {

	@Transactional
	public void gravar(PersonalTransaction personalTransaction) throws Exception {
		
		PersonalDTO ptd = new PersonalDTO();
		ptd.setId(personalTransaction.getId());
		ptd.setDateTime(personalTransaction.getDateTime());
		ptd.setTypeTransaction(new TransactionTypeDTO().setDescription(personalTransaction.getTypeTransaction().getDescription()));
	
		AccountDTO acc = new AccountDTO();
		acc.setCodigo("R1");
		acc.setDescription("Salario");
		acc.setId(null);
		acc.setSubgroup(null);
		ptd.setAccount(acc);
		ptd.setValue(1000D);
		gravar(ptd);
	}

}
