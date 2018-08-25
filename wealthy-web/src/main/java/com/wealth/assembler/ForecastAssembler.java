package com.wealth.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.wealth.common.forecast.ForecastDTO;
import com.wealth.controller.ForecastController;
import com.wealth.resource.ForecastResource;

public class ForecastAssembler extends ResourceAssemblerSupport<ForecastDTO, ForecastResource > {

	public ForecastAssembler() {
		super(ForecastDTO.class, ForecastResource.class);
	}

	@Override
	public ForecastResource toResource(ForecastDTO dto) {
		ForecastResource resource = new ForecastResource();
		resource.setAccount(dto.getAccount());
		resource.setAccSubGroup(dto.getAccSubGroup());
		resource.setCashin(dto.getCashin());
		resource.setPeriod(dto.getPeriod());
		resource.setTotal(dto.getTotal());
		Link link = linkTo(ForecastController.class).slash(dto.getId()).withSelfRel();
		resource.add(link);
		return resource;
	}
	
	

}
