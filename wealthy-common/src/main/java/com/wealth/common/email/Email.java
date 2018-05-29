package com.wealth.common.email;

public enum Email {
	
	RECOVER("Password Revover", "Your Pricelet password has been reset to : "),	

	RESET("Password Reset", "Your Pricelet password has been recently reset to :");	

	
	private String subject;
	  private String body;
	  
	  private Email(String subject, String body){
		  this.subject = subject;
		  this.body = body;
	  }

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	  
	  

}
