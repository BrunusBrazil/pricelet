package com.wealth.service;	

import static com.wealth.common.email.Email.RECOVER;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.wealth.common.email.Emailable;
import com.wealth.common.email.Emailer;
import com.wealthy.common.user.UserDTO;
import com.wealthy.common.user.UserService;

@Service("emailServiceImpl")
public class EmailServiceImpl {

	@Autowired
	@Qualifier(value = "userServiceImpl")
	private UserService userService;

	public Boolean recoverPassword(String msg) {
		boolean recovered = false;
		try {
			UserDTO user = userService.searchByEmail(msg);
			final String newPassword = generateNewPassword(user);
			user.setPassword(newPassword);
			userService.updatePassword(user);
			Emailer.sendEmail(new RecoveryEmail(user));		
		} catch (Exception e) {
			recovered = false;
		}
		return recovered;
	}
	
	class RecoveryEmail implements Emailable{
		final UserDTO user; 
		public RecoveryEmail(UserDTO user) {
			this.user = user;
		}
		public String getEmailSender() {
			return "default";
		}
		public String getEmailReceiver() {
			return user.getEmail();
		}
		public com.wealth.common.email.Email getEmailType() {
			return RECOVER;
		}
		public String getBody() {
			return RECOVER.getBody().concat(" ").concat(generateNewPassword(user));
		}
	}
	
	
	private String generateNewPassword(UserDTO user){
		return DigestUtils
				.sha256Hex(user.getId()
						.toString().
						concat(System.currentTimeMillis()+"")).substring(0,  5);
	}

}
