package com.wealth.data.accountgroup;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wealth.data.SuperEntity;

@Entity
@Table(name="acc_group")
public class AccountGroup extends SuperEntity {

	@Column(name="description", length=10, nullable=false)
	private String description;

//	@OneToMany(mappedBy="accountGroup" ,cascade= CascadeType.MERGE)
//	private Set<AccountSubGroup> subGroups = new HashSet<AccountSubGroup>();

		

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
