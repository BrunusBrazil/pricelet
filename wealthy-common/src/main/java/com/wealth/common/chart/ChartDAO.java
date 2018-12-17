package com.wealth.common.chart;

import java.sql.SQLException;
import java.time.LocalDate;

public interface ChartDAO {
	ChartDTO getTransactionsChart(LocalDate dateFrom, LocalDate dateTO, Integer userId) throws SQLException;
}
