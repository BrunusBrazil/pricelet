package com.wealth.resource;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.wealth.common.acctsubgroup.AccSubGroupDTO;

public class AccountResource extends ResourceSupport {

	private String description;
	private List<AccSubGroupDTO> subGroups = null;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<AccSubGroupDTO> getSubGroups() {
		return subGroups;
	}

	public void setSubGroups(List<AccSubGroupDTO> subGroups) {
		this.subGroups = subGroups;
	}

}
