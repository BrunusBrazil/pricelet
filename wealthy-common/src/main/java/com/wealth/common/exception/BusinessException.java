package com.wealth.common.exception;

public class BusinessException extends Exception {
	
	private static final long serialVersionUID = 5732053412687383022L;
 
	public BusinessException() {
	  super();
	}
	
	public BusinessException(String message) {
		super(message);
	}	
}
