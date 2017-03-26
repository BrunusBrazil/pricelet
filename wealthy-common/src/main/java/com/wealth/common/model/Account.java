package com.wealth.common.model;

public class Account {

	private Integer id;

	private String codigo;
	
	private String description;
	
	private Account subgroup;
		
	//getters and setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Account getSubgroup() {
		return subgroup;
	}

	public void setSubgroup(Account subgroup) {
		this.subgroup = subgroup;
	}
	
	
	
	
	
}
