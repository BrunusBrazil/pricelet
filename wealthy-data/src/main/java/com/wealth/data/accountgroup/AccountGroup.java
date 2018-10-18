package com.wealth.data.accountgroup;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.wealth.data.SuperEntity;
import com.wealth.data.accsubgroup.AccountSubGroup;

@Entity
@Table(name="acc_group")
public class AccountGroup extends SuperEntity {

	@Column(name="description", length=10, nullable=false)
	private String description;

	@OneToMany(mappedBy="account" ,cascade= CascadeType.ALL)
	private Set<AccountSubGroup> subGroups = new HashSet<AccountSubGroup>();

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
	
	public boolean equals(Object obj){
		if(this == obj )
			return true;
		if(obj == null)
			return true;
		
		if(obj instanceof AccountGroup){
			return ((AccountGroup) obj).getId().equals(this.getId());
		}
		else{
			return false;
		}
	}
	
}
