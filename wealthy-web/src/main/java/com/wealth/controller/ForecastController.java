package com.wealth.controller;

import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wealth.assembler.ForecastAssembler;
import com.wealth.common.forecast.ForecastDTO;
import com.wealth.common.forecast.ForecastService;
import com.wealth.resource.ForecastResource;
import com.wealthy.common.user.UserService;

@RestController
@RequestMapping(value="forecasts")
public class ForecastController extends AbstractController {

	@Autowired
	@Qualifier("forecastServiceImpl")
	private ForecastService service;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public  ResponseEntity<List<ForecastResource>> 
	getForecast(@RequestParam("period") @DateTimeFormat(iso = ISO.DATE_TIME) 
		LocalDate period, Principal principal) throws Exception{
		ForecastDTO dto = new ForecastDTO();
		dto.setPeriod(Date.valueOf(period));
		setPrincipal(principal, dto);
		final List<ForecastDTO> forecastList = service.getByDate(dto);
		ForecastAssembler aa = new ForecastAssembler();
		List<ForecastResource> ar = aa.toResources(forecastList);
		return new ResponseEntity<List<ForecastResource>>(ar, HttpStatus.ACCEPTED);
	}	

	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public  ResponseEntity<ForecastResource> 
	updateForecast(@PathVariable Integer id, @RequestBody ForecastDTO dto, Principal principal) throws Exception{
		setPrincipal(principal, dto);
		final ForecastDTO forecast = service.update((ForecastDTO) setPrincipal(principal, dto, id));
		ForecastAssembler aa = new ForecastAssembler();
		ForecastResource ar = aa.toResource(forecast);
		return new ResponseEntity<ForecastResource>(ar, HttpStatus.ACCEPTED);
	}	
	
	@RequestMapping(value="/period", method = RequestMethod.POST)
	public  ResponseEntity<List<ForecastResource>> 
	getForecast(@RequestBody ForecastDTO forecastDTO, Principal principal) throws Exception{
		setPrincipal(principal, forecastDTO);
		final List<ForecastDTO> forecastList = service.create(forecastDTO);
		ForecastAssembler aa = new ForecastAssembler();
		List<ForecastResource> ar = aa.toResources(forecastList);
		return new ResponseEntity<List<ForecastResource>>(ar, HttpStatus.ACCEPTED);
	}	

}


