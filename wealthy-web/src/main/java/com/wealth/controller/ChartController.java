package com.wealth.controller;

import java.security.Principal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wealth.common.chart.ChartDTO;
import com.wealth.common.chart.ChartService;
import com.wealthy.common.user.UserDTO;

@RestController
@RequestMapping(value="Chart")
public class ChartController extends AbstractController{
	
	@Autowired
	@Qualifier("chartServiceImpl")
	private ChartService service;
	
	@RequestMapping(value="/transactions", method = RequestMethod.GET)
	public ResponseEntity<ChartDTO> create(Principal principal, 
			@RequestParam("dateFrom") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDate dateFrom,
			@RequestParam("dateTo") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDate dateTo) throws Exception{
			UserDTO user = getCurrentUser(principal);
			ChartDTO chart = service.getTransactionsChart(dateFrom, dateTo, user.getId());
		return new ResponseEntity<ChartDTO>(chart, HttpStatus.CREATED);
	}
	
	
}
