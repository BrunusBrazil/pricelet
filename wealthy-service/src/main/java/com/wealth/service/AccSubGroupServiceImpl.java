package com.wealth.service;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.wealth.common.acctsubgroup.AccSubGroupDAO;
import com.wealth.common.acctsubgroup.AccSubGroupDTO;
import com.wealth.common.acctsubgroup.AccSubGroupService;
import com.wealth.common.exception.BusinessException;
import com.wealth.common.exception.ErrorDetail;

@Service("accSubtGroupServiceImpl")
public class AccSubGroupServiceImpl implements AccSubGroupService {

	@Autowired
	@Qualifier("accSubGroupDaoImpl")
	private AccSubGroupDAO dao;	
	
	@Override
	public AccSubGroupDTO merge(AccSubGroupDTO accSubGroupDTO) throws BusinessException {
		AccSubGroupDTO dto;
		String message = null;
		try {			
			
			if(accSubGroupDTO != null && accSubGroupDTO.getId()== null){
				message = ErrorDetail.DB_DML_INSERT.getDescription();
				dto = dao.create(accSubGroupDTO);
			}
			else{
				message = ErrorDetail.DB_DML_UPDATE.getDescription();
				dto = dao.update(accSubGroupDTO);
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
	public AccSubGroupDTO update(AccSubGroupDTO subGroup) throws BusinessException {
		return null;
	}

	@Override
	public List<AccSubGroupDTO> searchAll() throws BusinessException {
		List<AccSubGroupDTO> list = null;
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
	public List<AccSubGroupDTO> delete(Integer id) throws BusinessException {
		List<AccSubGroupDTO> list = null;
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
	public AccSubGroupDTO searchById(Integer id) throws BusinessException {
		AccSubGroupDTO dto;
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
