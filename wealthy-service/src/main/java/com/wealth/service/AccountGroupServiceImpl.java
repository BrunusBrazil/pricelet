package com.wealth.service;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.wealth.common.accountgroup.AccountGroupDAO;
import com.wealth.common.accountgroup.AccountGroupDTO;
import com.wealth.common.accountgroup.AccountGroupService;
import com.wealth.common.exception.BusinessException;
import com.wealth.common.exception.ErrorDetail;


@Service("accountGroupServiceImpl")
public class AccountGroupServiceImpl implements AccountGroupService {

	@Autowired
	@Qualifier("accountGroupDaoImpl")
	private AccountGroupDAO dao;

	@Override
	public AccountGroupDTO merge(AccountGroupDTO account) throws BusinessException {
		AccountGroupDTO dto;
		String message = null;
		try {			
			
			if(account != null && account.getId()== null){
				message = ErrorDetail.DB_DML_INSERT.getDescription();
				dto = dao.create(account);
			}
			else{
				message = ErrorDetail.DB_DML_UPDATE.getDescription();
				dto = dao.update(account);
			}
		}
		catch (SQLException | PersistenceException e) {
			throw new  BusinessException(message);
		}
		catch (Exception e) {
			throw new  BusinessException(e.getMessage());
		}
		return dto;
	}

	@Override
	public List<AccountGroupDTO> searchAll() throws BusinessException {
		List<AccountGroupDTO> list = null;
		try {
			list = dao.searchAll();
		}catch (SQLException | PersistenceException e) {
				throw new  BusinessException(ErrorDetail.DB_DML_SEARCH.getDescription());
		}
		catch (Exception e) {
				throw new  BusinessException(e.getMessage());
		}
		return list;
	}

	@Override
	public List<AccountGroupDTO> delete(Integer id) throws BusinessException {
		List<AccountGroupDTO> list = null;
		try {
			dao.delete(id);
			list = dao.searchAll();
		}catch (SQLException | PersistenceException e) {
				throw new  BusinessException(ErrorDetail.DB_DML_DELETE.getDescription());
		}
		catch (Exception e) {
				throw new  BusinessException(e.getMessage());
		}
		return list;
	}
	
	@Override
	public AccountGroupDTO searchById(Integer id) throws BusinessException{
		AccountGroupDTO dto;
		try {
			dto = dao.searchById(id);
		}catch (SQLException | PersistenceException e) {
				throw new  BusinessException(ErrorDetail.DB_DML_SEARCH.getDescription());
		}
		catch (Exception e) {
				throw new  BusinessException(e.getMessage());
		}
		return dto;	
	};
	
	
}
