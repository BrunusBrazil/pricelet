	package com.wealth.data.forecast;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wealth.common.forecast.ForecastDAO;
import com.wealth.common.forecast.ForecastDTO;
import com.wealth.data.GenericDaoImpl;

@Transactional
@Component("forecastDAOImpl")
public class ForecastDAOImpl extends GenericDaoImpl implements ForecastDAO {

	@Autowired
    private DozerBeanMapper dozerBeanMapper;
	
	@Override
	public List<ForecastDTO> update(List<ForecastDTO> forecastListDTO) throws SQLException {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ForecastDTO> getByDate(ForecastDTO forecastDTO) throws SQLException {
		List<ForecastDTO> ac  = null;
		Date period = new Date(forecastDTO.getPeriod().getTime());
		StringBuilder query = new StringBuilder(20);
		LocalDate localDate  =  period.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		YearMonth yearMonth = YearMonth.from(localDate);
		
		query.append(" select f from Forecast f where");
		query.append(" DATE_FORMAT(f.period, '%Y-%m') = '").append(yearMonth).append("' and");
		query.append(" f.userId = '").append(forecastDTO.getUserId()).append("'");
		
		try {
			ac = (List<ForecastDTO>) getEm().createQuery(query.toString()).getResultList()
					.stream()
					.map(b-> dozerBeanMapper.map(b, ForecastDTO.class))
					.collect(Collectors.toList());
		} catch (NoResultException noResultException) {
			throw noResultException;
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
		return ac;
	}

	@Override
	public List<ForecastDTO> create(ForecastDTO forecastDTO) throws SQLException {
		List<ForecastDTO> forecastsResult = new ArrayList<ForecastDTO>();
		Query query = getEm().createNativeQuery(
				" insert into forecast(acc_group_id, acc_subgroup_id, user_id, cashin, period, total) "
				+ "  select sg.acc_group_id, sg.id, 1, 0, :period, 0"
				+ "  from acc_subgroup sg where user_id = :userId");

		query.setParameter("period", forecastDTO.getPeriod());
		
		query.setParameter("userId",forecastDTO.getUserId());
		
		int rowsUpdated = query.executeUpdate();
		
		if(rowsUpdated > 0 ){
			forecastsResult = getByDate(forecastDTO);
		}
		return forecastsResult;
	}

	@Override
	public List<ForecastDTO> createFocastForNextMonth() throws SQLException {
		return null;
	}

	@Override
	public ForecastDTO update(ForecastDTO forecastDTO) throws SQLException {
		Forecast forecast = (Forecast) super.alterar(dozerBeanMapper.map(forecastDTO, Forecast.class));
		ForecastDTO dto =  dozerBeanMapper.map(forecast, ForecastDTO.class);
		return dto;
	}

}
