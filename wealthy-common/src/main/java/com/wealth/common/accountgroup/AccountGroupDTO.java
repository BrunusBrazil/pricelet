package com.wealth.common.accountgroup;

import java.util.Objects;

import com.wealth.common.ModelDTO;

public class AccountGroupDTO  extends ModelDTO{

	private String description;

	@Override
	public boolean equals(Object object){
		if(object == this){
			return true;
		}
		else if(object instanceof AccountGroupDTO) {
			AccountGroupDTO agdto =	(AccountGroupDTO) object;
 		    return  Objects.equals(this.getId(), agdto.getId()) && 
 		    		Objects.equals(this.getDescription(), agdto.getDescription());  
 	 	}
		else{
			return false;
		}
	}
	
	public static void main(String[] args) {
		AccountGroupDTO agdto1 = new AccountGroupDTO();
		AccountGroupDTO agdto2 = new AccountGroupDTO();
		AccountGroupDTO agdto3 = new AccountGroupDTO();
		
		//1- it is reflexive
		System.out.println("agdto1.equals(agdto1) == "+ agdto1.equals(agdto1));

		//2- it is symmetric
		System.out.println("agdto1.equals(agdto2) == "+ agdto1.equals(agdto2));
		System.out.println("agdto2.equals(agdto1) == "+ agdto2.equals(agdto1));

		//3- it is transitive
		agdto1.setDescription("test");
		agdto2.setDescription("test");
		agdto3.setDescription("test");
		
		System.out.println("agdto1.equals(agdto2) == "+ agdto1.equals(agdto2));
		System.out.println("agdto2.equals(agdto3) == "+ agdto2.equals(agdto3));
		System.out.println("agdto3.equals(agdto1) == "+ agdto3.equals(agdto1));
		
		//4- it is consistent
		//5- for any non-null x, x.quals(null) is equal to false
		

		
	
	}
	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	

}
