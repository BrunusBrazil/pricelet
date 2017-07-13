package com.wealth.common.exception;

public enum ErrorDetail {
	
	DB_DML_INSERT("Could not insert", "DB"),
	DB_DML_SEARCH("Could not search", "DB"),
	DB_DML_DELETE("Could not update", "DB"),
	DB_DML_UPDATE("Could not update", "DB");
	
	private String description;
	private String type;
		
	private ErrorDetail(String description, String type) {
		this.description  = description;
		this.type = type;
	}
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	
}
