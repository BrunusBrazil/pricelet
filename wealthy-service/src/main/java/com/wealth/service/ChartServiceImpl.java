package com.wealth.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.wealth.common.chart.ChartDAO;
import com.wealth.common.chart.ChartDTO;
import com.wealth.common.chart.ChartService;
import com.wealth.common.exception.BusinessException;


@Service("chartServiceImpl")
public class ChartServiceImpl implements ChartService {

	@Autowired
	@Qualifier("chartDaoImpl")
	private ChartDAO dao;

	@Override
	public ChartDTO getTransactionsChart(LocalDate dateFrom, LocalDate dateTO, Integer userId) throws BusinessException {
		ChartDTO chart = new ChartDTO();
		try{
			chart = dao.getTransactionsChart(dateFrom, dateTO, userId);
		}catch(Exception e){
			throw new BusinessException(e.getMessage());
		}
		return chart;
	}

	
}
