package com.wealth.common.email;

public interface Emailable {
	String getEmailSender();
	String getEmailReceiver();
	Email getEmailType();
	String getBody();
}
