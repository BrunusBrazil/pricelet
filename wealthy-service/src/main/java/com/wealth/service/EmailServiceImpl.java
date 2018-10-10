package com.wealth.service;	

import static com.wealth.common.email.Email.RECOVER;
import static com.wealth.common.email.Email.NEW_USER; 
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.wealth.common.email.Email;
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
	
	public void sendEMailNewUser(UserDTO user){
		Emailer.sendEmail(new NewUserEmail(user));		
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
			return RECOVER.getBody().concat(" ").concat(user.getPassword());
		}
	}
	
	class NewUserEmail implements Emailable{
		UserDTO user;
		
		public NewUserEmail(UserDTO user) {
			this.user = user;
			
		}
	
		@Override
		public String getEmailSender() {
			return "default";
		}

		@Override
		public String getEmailReceiver() {
			return user.getEmail();
		}

		@Override
		public Email getEmailType() {
			return Email.NEW_USER;
		}

		@Override
		public String getBody() {
			return NEW_USER.getBody()
					.concat(" username: ").concat(user.getUserName())
					.concat(", password: ").concat(user.getPassword());
		}
		
	}
	
	public String generateNewPassword(UserDTO user){
		return DigestUtils
				.sha256Hex(user.getId()
						.toString().
						concat(System.currentTimeMillis()+"")).substring(0,  9);
	}

	public String generateNewPassword(){
		return DigestUtils
				.sha256Hex(System.currentTimeMillis()+"").substring(0,  9);
	}

}
