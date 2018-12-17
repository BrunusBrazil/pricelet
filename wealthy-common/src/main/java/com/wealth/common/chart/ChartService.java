package com.wealth.common.chart;

import java.time.LocalDate;

import com.wealth.common.exception.BusinessException;

public interface ChartService {
	ChartDTO getTransactionsChart(LocalDate dateFrom, LocalDate dateTO, Integer userId) throws BusinessException;
}

