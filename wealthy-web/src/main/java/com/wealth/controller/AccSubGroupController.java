	package com.wealth.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wealth.assembler.AccSubGroupAssembler;
import com.wealth.common.acctsubgroup.AccSubGroupDTO;
import com.wealth.common.acctsubgroup.AccSubGroupService;
import com.wealth.common.exception.BusinessException;
import com.wealth.common.forecast.ForecastDTO;
import com.wealth.resource.AccSubGroupResource;
import com.wealth.service.ForecastServiceImpl;

@RestController
@RequestMapping(value="AccSubGroup")
public class AccSubGroupController extends AbstractController {
	
	@Autowired
	@Qualifier("accSubGroupServiceImpl")
	private AccSubGroupService service;
	
	@Autowired
	@Qualifier("forecastServiceImpl")
	private ForecastServiceImpl forecastService;

	
	@RequestMapping(value="/", method = RequestMethod.POST)
	public ResponseEntity<AccSubGroupResource> create(@RequestBody AccSubGroupDTO subAccount, Principal principal) throws Exception{
		setPrincipal(principal, subAccount);
		setPrincipal(principal, subAccount.getAccount());		
		AccSubGroupDTO accSubGroupDTO  =  service.merge((AccSubGroupDTO) setPrincipal(principal, subAccount));
		createForecast(subAccount);
		AccSubGroupAssembler aa = new AccSubGroupAssembler();
		AccSubGroupResource ar = aa.toResource(accSubGroupDTO);
		return new ResponseEntity<AccSubGroupResource>(ar, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ResponseEntity<List<AccSubGroupResource>> searchAll(Principal principal) throws Exception{
		List<AccSubGroupDTO> accSubGroupDTO  = 
				service.searchAll((AccSubGroupDTO) setPrincipal(principal, new AccSubGroupDTO()));
		AccSubGroupAssembler aa = new AccSubGroupAssembler();
		List<AccSubGroupResource> ar = aa.toResources(accSubGroupDTO);
		return ResponseEntity.ok(ar);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<List<AccSubGroupResource>> delete(@PathVariable Integer id, Principal principal) throws Exception{
		List<AccSubGroupDTO> accSubGroupDTO  = 
				service.delete((AccSubGroupDTO) setPrincipal(principal, new AccSubGroupDTO(), id));
		AccSubGroupAssembler aa = new AccSubGroupAssembler();
		List<AccSubGroupResource> ar = aa.toResources(accSubGroupDTO);
		return ResponseEntity.ok(ar);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public  ResponseEntity<AccSubGroupResource> update(@PathVariable Integer id,  
			@RequestBody AccSubGroupDTO account, Principal principal) throws Exception{
		AccSubGroupDTO accountGroupDTO  =	service.merge((AccSubGroupDTO) setPrincipal(principal, account));
		AccSubGroupAssembler aa = new AccSubGroupAssembler();
		AccSubGroupResource ar = aa.toResource(accountGroupDTO);
		return new ResponseEntity<AccSubGroupResource>(ar, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public  ResponseEntity<AccSubGroupResource> searchById(@PathVariable Integer id, Principal principal) throws Exception{
		AccSubGroupDTO accountGroupDTO  = 
				service.searchById((AccSubGroupDTO) setPrincipal(principal, new AccSubGroupDTO(), id));
		AccSubGroupAssembler aa = new AccSubGroupAssembler();
		AccSubGroupResource ar = aa.toResource(accountGroupDTO);
		return new ResponseEntity<AccSubGroupResource>(ar, HttpStatus.ACCEPTED);
	}
	
	
	/* Updates forecast list for current period 
	 * Given a forecast for current month exists 
	 * Then updates this list of forecast with the new inserted account
	 * */
	private void createForecast(AccSubGroupDTO account) throws BusinessException{
		ForecastDTO forecastDTO = new ForecastDTO();
		forecastDTO.setPeriod(new Date());
		forecastDTO.setUserId(account.getUserId());
		if(!Optional.ofNullable(forecastService.getByDate(forecastDTO)).isPresent()){
			
			forecastService.create(account);
		}
	}
}