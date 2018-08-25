package com.wealth.assembler;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import com.wealth.common.xtransaction.XTransactionDTO;
import com.wealth.controller.XtransactionController;
import com.wealth.resource.XTransactionResource;

public class XTransactionAssembler extends ResourceAssemblerSupport<XTransactionDTO, XTransactionResource > {

	public XTransactionAssembler() {
		super(XTransactionDTO.class, XTransactionResource.class);
	}

	@Override
	public XTransactionResource toResource(XTransactionDTO dto) {
		XTransactionResource resource = new XTransactionResource();
		resource.setAccount(dto.getAccount());
		resource.setAccSubGroup(dto.getAccSubGroup());
		resource.setValor(dto.getValor());
		resource.setType(dto.getType());
		resource.setLastUpdate(dto.getLastUpdate());
		resource.setEntrada(dto.getEntrada());
		resource.setDescription(dto.getDescription());
		resource.setDateTransaction(dto.getDateTransaction());
		resource.setCrete(dto.getCreate());
		Link link = linkTo(XtransactionController.class).slash(dto.getId()).withSelfRel();
		resource.add(link);
		return resource;
	}
	
	

}
