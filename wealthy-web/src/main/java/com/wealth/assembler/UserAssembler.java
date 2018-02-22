package com.wealth.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.wealth.controller.UserController;
import com.wealth.resource.UserResource;
import com.wealthy.common.user.UserDTO;

public class UserAssembler extends ResourceAssemblerSupport<UserDTO, UserResource > {

	public UserAssembler() {
		super(UserDTO.class, UserResource.class);
	}

	@Override
	public UserResource toResource(UserDTO dto) {
		UserResource resource = new UserResource();
		resource.setFullName(dto.getFullName());
		resource.setEmail(dto.getEmail());
		resource.setUserName(dto.getUserName());
		Link link = linkTo(UserController.class).slash(dto.getId()).withSelfRel();
		resource.add(link);
		return resource;
	}
	
}
