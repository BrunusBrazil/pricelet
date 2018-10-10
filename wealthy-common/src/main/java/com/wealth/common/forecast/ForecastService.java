package com.wealth.common.forecast;

import java.util.List;

import com.wealth.common.acctsubgroup.AccSubGroupDTO;
import com.wealth.common.exception.BusinessException;

public interface ForecastService {
	List<ForecastDTO> update(List<ForecastDTO> forecastListDTO) throws BusinessException;
	List<ForecastDTO> getByDate(ForecastDTO forecastDTO) throws BusinessException;
	List<ForecastDTO> create(ForecastDTO forecastDTO) throws BusinessException;
	ForecastDTO update(ForecastDTO forecastListDTO) throws BusinessException;
	ForecastDTO create(AccSubGroupDTO subGroupDTO) throws BusinessException;

}

