package com.wealth.data.accountgroup;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wealth.common.accountgroup.AccountGroupDAO;
import com.wealth.common.accountgroup.AccountGroupDTO;
import com.wealth.data.GenericDaoImpl;

@Transactional
@Component("accountGroupDaoImpl")
public class AccountGroupDAOImpl extends GenericDaoImpl implements  AccountGroupDAO{
	
	@Autowired
    DozerBeanMapper dozerBeanMapper;
	
	@Override
	public AccountGroupDTO create(AccountGroupDTO account) throws SQLException {
		return converTotDTO((AccountGroup)super.gravar(convertToDAO(account)));
	}

	@Override
	public AccountGroupDTO update(AccountGroupDTO account) throws SQLException {
		return converTotDTO((AccountGroup)super.alterar(convertToDAO(account)));
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<AccountGroupDTO> searchAll(AccountGroupDTO account) throws SQLException {
		List<AccountGroupDTO>  ac = null;
		StringBuilder query = new StringBuilder(20);
		query.append("select acg from AccountGroup acg where acg.userId = ").append(account.getUserId());
		
		try {
			ac = convertAccountsToDTO(getEm().createQuery(query.toString()).getResultList());
		} catch (NoResultException noResultException) {
			throw noResultException;
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
		return ac;
	}

	@Override
	public void delete(AccountGroupDTO account) throws SQLException {
		StringBuilder query = new StringBuilder(20);
		query.append("delete AccountGroup acg ");
		query.append(" where acg.id = " ).append(account.getId());
		query.append(" and acg.userId = " ).append(account.getUserId());
		
		try {
			 getEm().createQuery(query.toString()).executeUpdate();
		} catch (NoResultException noResultException) {
			throw noResultException;
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	
	
	@Override
	public AccountGroupDTO searchById(AccountGroupDTO account) throws SQLException {
		AccountGroupDTO ac = null;
		StringBuilder query = new StringBuilder(20);
		query.append("select acg from AccountGroup acg ");
		query.append(" where acg.id = " ).append(account.getId());
		query.append(" and acg.userId = " ).append(account.getUserId());
		try {
			ac = converTotDTO((AccountGroup) getEm().createQuery(query.toString()).getSingleResult());
		} catch (NoResultException noResultException) {
			throw noResultException;
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
		return ac;
	}
	
	public AccountGroupDTO converTotDTO(AccountGroup dao){
		return dozerBeanMapper.map(dao, AccountGroupDTO.class);
	}

	public  List<AccountGroupDTO> convertAccountsToDTO(List<AccountGroup> list){
		List<AccountGroupDTO> newList = new ArrayList<>();
		for(AccountGroup acg: list ){
			if(!newList.contains(acg))
			newList.add(dozerBeanMapper.map(acg,AccountGroupDTO.class));
		}		
		return newList;
	}

	public AccountGroup convertToDAO(AccountGroupDTO acGroupDTO){
		return dozerBeanMapper.map(acGroupDTO, AccountGroup.class);
	}
}
