package com.wealth.controller;

import java.util.List;

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
import com.wealth.resource.AccSubGroupResource;

@RestController
@RequestMapping(value="AccSubGroup")
public class AccSubGroupController {
	
	@Autowired
	@Qualifier("accSubtGroupServiceImpl")
	private AccSubGroupService service;
	
	@RequestMapping(value="/", method = RequestMethod.POST)
	public ResponseEntity<AccSubGroupResource> create(@RequestBody AccSubGroupDTO account) throws Exception{
		AccSubGroupDTO accSubGroupDTO  = service.merge(account);
		AccSubGroupAssembler aa = new AccSubGroupAssembler();
		AccSubGroupResource ar = aa.toResource(accSubGroupDTO);
		return new ResponseEntity<AccSubGroupResource>(ar, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ResponseEntity<List<AccSubGroupResource>> searchAll() throws Exception{
		List<AccSubGroupDTO> accSubGroupDTO  = service.searchAll();
		AccSubGroupAssembler aa = new AccSubGroupAssembler();
		List<AccSubGroupResource> ar = aa.toResources(accSubGroupDTO);
		return ResponseEntity.ok(ar);
	}
	
	@RequestMapping(value="/", method = RequestMethod.DELETE)
	public ResponseEntity<List<AccSubGroupResource>> delete(@RequestBody AccSubGroupDTO subGroupDTO) throws Exception{
		List<AccSubGroupDTO> accSubGroupDTO  = service.delete(subGroupDTO);
		AccSubGroupAssembler aa = new AccSubGroupAssembler();
		List<AccSubGroupResource> ar = aa.toResources(accSubGroupDTO);
		return ResponseEntity.ok(ar);
	}
	
	@RequestMapping(value="/", method = RequestMethod.PUT)
	public  ResponseEntity<AccSubGroupResource> update(@RequestBody AccSubGroupDTO account) throws Exception{
		AccSubGroupDTO accountGroupDTO  = service.merge(account);
		AccSubGroupAssembler aa = new AccSubGroupAssembler();
		AccSubGroupResource ar = aa.toResource(accountGroupDTO);
		return new ResponseEntity<AccSubGroupResource>(ar, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public  ResponseEntity<AccSubGroupResource> searchById(@PathVariable Integer id) throws Exception{
		AccSubGroupDTO accountGroupDTO  = service.searchById(id);
		AccSubGroupAssembler aa = new AccSubGroupAssembler();
		AccSubGroupResource ar = aa.toResource(accountGroupDTO);
		return new ResponseEntity<AccSubGroupResource>(ar, HttpStatus.ACCEPTED);
	}
	
}