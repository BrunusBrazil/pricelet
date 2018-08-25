package com.wealth.common.forecast;

import java.sql.SQLException;
import java.util.List;

public interface ForecastDAO {
	List<ForecastDTO> update(List<ForecastDTO> forecastListDTO) throws SQLException;
	List<ForecastDTO> getByDate(ForecastDTO forecastDTO) throws SQLException;
	List<ForecastDTO> create(ForecastDTO forecastDTO) throws SQLException;
	ForecastDTO update(ForecastDTO forecastListDTO) throws SQLException;
	List<ForecastDTO> createFocastForNextMonth()throws SQLException; }
