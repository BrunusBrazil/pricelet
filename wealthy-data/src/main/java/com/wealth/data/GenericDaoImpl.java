package com.wealth.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.springframework.transaction.annotation.Transactional;

public class GenericDaoImpl implements Dao {
	
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public Object gravar(Object object) throws PersistenceException {
		try {
			em.persist(object);
			em.flush();
		} catch (Exception e) {
			throw new PersistenceException(e);			
		}
		return object;
	}

	@Transactional
	public void excluir(Object object) throws PersistenceException {
		try {
			em.remove(em.contains(object)? object : em.merge(object));
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	@Transactional
	public Object alterar(Object object) throws PersistenceException {
		Object retorno = null;
		try {
			retorno = em.merge(object);
			em.flush();
			return retorno;
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}


}
