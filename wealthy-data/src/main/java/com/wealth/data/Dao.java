package com.wealth.data;

import javax.persistence.PersistenceException;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface Dao {

	@Transactional(propagation=Propagation.REQUIRED)
	Object gravar(Object object) throws PersistenceException;

	@Transactional(propagation=Propagation.REQUIRED)
	void excluir(Object object) throws PersistenceException;

	@Transactional(propagation=Propagation.REQUIRED)
	Object alterar(Object object) throws PersistenceException;
}
