package com.wealth.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class AccountDTO {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name="cod", length=3, nullable=false) 
	private String codigo;
	
	@Column(name="description", length=10, nullable=false)
	private String description;
	
	@Column(name="subgroup")
	private AccountDTO subgroup;
		
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

	public AccountDTO getSubgroup() {
		return subgroup;
	}

	public void setSubgroup(AccountDTO subgroup) {
		this.subgroup = subgroup;
	}
	
	
	
	
	
}
