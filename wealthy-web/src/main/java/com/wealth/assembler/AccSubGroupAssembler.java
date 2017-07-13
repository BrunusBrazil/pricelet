package com.wealth.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.wealth.common.acctsubgroup.AccSubGroupDTO;
import com.wealth.controller.AccSubGroupController;
import com.wealth.resource.AccSubGroupResource;

public class AccSubGroupAssembler extends ResourceAssemblerSupport<AccSubGroupDTO, AccSubGroupResource > {

	public AccSubGroupAssembler() {
		super(AccSubGroupDTO.class, AccSubGroupResource.class);
	}

	@Override
	public AccSubGroupResource toResource(AccSubGroupDTO dto) {
		AccSubGroupResource ac = new AccSubGroupResource();
		ac.setDescription(dto.getDescription());
		ac.setAccount(dto.getAccount());
		Link link = linkTo(AccSubGroupController.class).slash(dto.getId()).withSelfRel();
	    ac.add(link);
	    return ac;
	}

}
