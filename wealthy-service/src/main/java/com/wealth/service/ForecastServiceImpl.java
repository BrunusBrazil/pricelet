package com.wealth.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.wealth.common.exception.BusinessException;
import com.wealth.common.exception.ErrorDetail;
import com.wealth.common.forecast.ForecastDAO;
import com.wealth.common.forecast.ForecastDTO;
import com.wealth.common.forecast.ForecastService;

@Service("forecastServiceImpl")
public class ForecastServiceImpl implements ForecastService {

	@Autowired
	@Qualifier("forecastDAOImpl")
	private ForecastDAO dao;

	@Autowired
	@Qualifier("userServiceImpl")
	private UserServiceImpl userService;

	@Override
	public List<ForecastDTO> update(List<ForecastDTO> forecastListDTO) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ForecastDTO> getByDate(ForecastDTO forecastDTO) throws BusinessException {
		List<ForecastDTO> dto;
		try {
			dto = dao.getByDate(forecastDTO);
		} catch (SQLException | PersistenceException e) {
			throw new BusinessException(ErrorDetail.DB_DML_SEARCH.getDescription());
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		return dto;
	}

	@Override
	public List<ForecastDTO> create(ForecastDTO forecastDTO) throws BusinessException {
		List<ForecastDTO>  result = new ArrayList<ForecastDTO>();
		try {
			
			if(Objects.isNull(forecastDTO.getPeriod())){
				throw new  BusinessException("Period is mandatory");
			}
			
			if(Objects.isNull(forecastDTO.getUserId())){
				throw new  BusinessException("User not found");
			}
			List<ForecastDTO> forecastList  = getByDate(forecastDTO);
			
			if(CollectionUtils.isEmpty(forecastList)){
				result = dao.create(forecastDTO);
			}else{
				throw new  BusinessException("Forecast already exist for this period");
			}
			
		}catch(PersistenceException e){
				throw new  BusinessException(e);
		}
		catch (SQLException e) {
				throw new  BusinessException(ErrorDetail.DB_DML_INSERT.getDescription());
		}
		catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public ForecastDTO update(ForecastDTO forecastDTO) throws BusinessException {
		ForecastDTO dto;
		try {
				dto = dao.update(forecastDTO);
		}
		catch (SQLException | PersistenceException e) {
 			throw new  BusinessException(ErrorDetail.DB_DML_UPDATE.getDescription());
		}
		catch (Exception e) {
			throw new  BusinessException(e.getMessage());
		}
		return dto;
	}
	
}
