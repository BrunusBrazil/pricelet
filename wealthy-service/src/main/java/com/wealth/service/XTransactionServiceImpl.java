package com.wealth.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.wealth.common.exception.BusinessException;
import com.wealth.common.exception.ErrorDetail;
import com.wealth.common.xtransaction.XTransactionDAO;
import com.wealth.common.xtransaction.XTransactionDTO;
import com.wealth.common.xtransaction.XTransactionService;

@Service("xTransactionServiceImpl")
public class XTransactionServiceImpl implements XTransactionService {

	@Autowired
	@Qualifier("xtransactionalDaoImpl")
	private XTransactionDAO dao;

	@Override
	public List<XTransactionDTO> searchAll() throws BusinessException {
		List<XTransactionDTO> list = null;
		try {
			list = dao.searchAll();
		} catch (SQLException | PersistenceException e) {
			throw new BusinessException(ErrorDetail.DB_DML_SEARCH.getDescription());
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		return list;

	}

	@Override
	public XTransactionDTO merge(XTransactionDTO dto) throws BusinessException {
		XTransactionDTO x;
		String message = null;
		try {			
			dto.setDateTransaction(new Date());
			if(dto != null && dto.getId()== null){
				dto.setCrete(new Date());
				message = ErrorDetail.DB_DML_INSERT.getDescription();
				x = dao.create(dto);
			}
			else{
				message = ErrorDetail.DB_DML_UPDATE.getDescription();
				x = dao.update(dto);
			}
		}
		catch (SQLException | PersistenceException e) {
 			throw new  BusinessException(message);
		}
		catch (Exception e) {
			throw new  BusinessException(e.getMessage());
		}
		return x;
	}

	@Override
	public List<XTransactionDTO> delete(Integer id) throws BusinessException {
		List<XTransactionDTO> list = null;
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
	public XTransactionDTO searchById(Integer id) throws BusinessException {
		XTransactionDTO dto;
		try {
			dto = dao.searchById(id);
		}catch (SQLException | PersistenceException e) {
				throw new  BusinessException(ErrorDetail.DB_DML_SEARCH.getDescription());
		}
		catch (Exception e) {
				throw new  BusinessException(e.getMessage());
		}
		return dto;	
	}

}
