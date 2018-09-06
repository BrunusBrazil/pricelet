package com.wealth.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
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
import com.wealth.common.forecast.ForecastDTO;

@Service("accSubGroupServiceImpl")
public class AccSubGroupServiceImpl implements AccSubGroupService {

	@Autowired
	@Qualifier("accSubGroupDaoImpl")
	private AccSubGroupDAO dao;	

	@Autowired
	@Qualifier("forecastServiceImpl")
	private ForecastServiceImpl forecastService;
	
	@Override
	public AccSubGroupDTO merge(AccSubGroupDTO accSubGroupDTO) throws BusinessException {
		AccSubGroupDTO dto;
		String message = null;
		try {			
			
			if(accSubGroupDTO != null && accSubGroupDTO.getId()== null){
				message = ErrorDetail.DB_DML_INSERT.getDescription();
				dto = dao.create(accSubGroupDTO);
				
				ForecastDTO forecastDTO = new ForecastDTO();
				forecastDTO.setAccount(dto.getAccount());
				forecastDTO.setUserId(dto.getUserId());
				forecastDTO.setPeriod(new Date());
				forecastDTO.setTotal(new BigDecimal(0));
				forecastDTO.setCashin(false);
				forecastDTO.setAccSubGroup(dto);
				forecastService.create(forecastDTO);
				
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
	public List<AccSubGroupDTO> searchAll(AccSubGroupDTO subGroup) throws BusinessException {
		List<AccSubGroupDTO> list = null;
		try {
			list = dao.searchAll(subGroup);
		}catch (SQLException | PersistenceException e) {
				throw new  BusinessException(ErrorDetail.DB_DML_SEARCH.getDescription());
		}
		catch (Exception e) {
				throw new  BusinessException(e.getMessage());
		}
		return list;
	}

	@Override
	public List<AccSubGroupDTO> delete(AccSubGroupDTO subGroup) throws BusinessException {
		List<AccSubGroupDTO> list = null;
		try {
			dao.delete(subGroup);
			list = dao.searchAll(subGroup);
		}catch (SQLException | PersistenceException e) {
				throw new  BusinessException(ErrorDetail.DB_DML_DELETE.getDescription());
		}
		catch (Exception e) {
				throw new  BusinessException(e.getMessage());
		}
		return list;
		
	}

	@Override
	public AccSubGroupDTO searchById(AccSubGroupDTO subGroup) throws BusinessException {
		AccSubGroupDTO dto;
		try {
			dto = dao.searchById(subGroup);
		}catch (SQLException | PersistenceException e) {
				throw new  BusinessException(ErrorDetail.DB_DML_SEARCH.getDescription());
		}
		catch (Exception e) {
				throw new  BusinessException(e.getMessage());
		}
		return dto;	
	}

}
