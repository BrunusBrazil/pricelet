package com.wealth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.wealth.personal.PersonalTransacionDAO;
import com.wealth.personal.PersonalTransaction;
import com.wealth.personal.PersonalTransactionService;

@Service("personalTransactionService")
public class PersonalTransactionServiceImpl implements PersonalTransactionService{

	@Autowired
	@Qualifier("personalTransactionDAOImpl")
	private PersonalTransacionDAO dao;
	
	@Override
	public void gravar(PersonalTransaction personalTransaction) throws Exception {
		try {
			dao.gravar(personalTransaction);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
