package com.wealth.data.accountgroup;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="acc_group")
public class AccountGroup {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name="description", length=10, nullable=false)
	private String description;

//	@OneToMany(mappedBy="accountGroup" ,cascade= CascadeType.MERGE)
//	private Set<AccountSubGroup> subGroups = new HashSet<AccountSubGroup>();

	//setters and setters	
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
	
	public boolean equals(Object obj){
		if(this == obj )
			return true;
		if(obj == null)
			return true;
		
		if(obj instanceof AccountGroup){
			return ((AccountGroup) obj).getId().equals(this.id);
		}
		else{
			return false;
		}
	}
	
	
	
}
