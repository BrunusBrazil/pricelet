package com.wealth.data.accsubgroup;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wealth.data.accountgroup.AccountGroup;

@Entity
@Table(name="acc_subgroup")
public class AccountSubGroup {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name="description", length=10, nullable=false)
	private String description;
		
    @ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="acc_group_id")
	private AccountGroup account;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AccountGroup getAccount() {
		return account;
	}

	public void setAccount(AccountGroup account) {
		this.account = account;
	}

	
}
