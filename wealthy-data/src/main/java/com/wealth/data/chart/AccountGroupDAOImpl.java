package com.wealth.data.chart;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.apache.commons.lang3.ArrayUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wealth.common.chart.ChartDAO;
import com.wealth.common.chart.ChartDTO;
import com.wealth.data.GenericDaoImpl;

@Transactional
@Component("chartDaoImpl")
public class AccountGroupDAOImpl extends GenericDaoImpl  implements ChartDAO{
	
	@Autowired
    DozerBeanMapper dozerBeanMapper;

	@Override
	public ChartDTO getTransactionsChart(LocalDate dateFrom, LocalDate dateTO, Integer userId) throws SQLException {
		ChartDTO chart = new ChartDTO();	
		try {
			Map<String, List<Object>> map = new HashMap<>(); 
			map.put("EXPENSES", getTransactionsByStoredProcedure(dateFrom, dateTO, userId, "annualExpense"));
			map.put("INCOMES", getTransactionsByStoredProcedure(dateFrom, dateTO, userId, "annualIncome"));
			chart.setLabels(Arrays.asList((Object[])new DateFormatSymbols().getMonths()).subList(0,12));
			chart.setData(map);
		} catch (Exception e) {
			throw e;
		}
		return chart;
	}
	
	private List<Object> getTransactionsByStoredProcedure(LocalDate from, LocalDate to, Integer userId, String procedureName){
		StoredProcedureQuery sp =  getEm().createStoredProcedureQuery(procedureName);
		sp.registerStoredProcedureParameter("date_from", Date.class, ParameterMode.IN);
		sp.registerStoredProcedureParameter("date_to", Date.class, ParameterMode.IN);
		sp.registerStoredProcedureParameter("user_id", Integer.class, ParameterMode.IN);
		sp.setParameter("date_from", java.sql.Date.valueOf(from));
		sp.setParameter("date_to",java.sql.Date.valueOf(to));
		sp.setParameter("user_id", userId);
		sp.execute();
		List<Object[]> result = sp.getResultList();
		return (List<Object>) result.stream().map(r -> {return (Object) r[1];}).collect(Collectors.toList());
	}
}
