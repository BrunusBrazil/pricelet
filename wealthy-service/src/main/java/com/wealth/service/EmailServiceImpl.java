package com.wealth.service;

import static com.wealth.common.email.Email.RECOVER;

import javax.persistence.NoResultException;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.config.TransportStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
			Email email = EmailBuilder.startingBlank()
					.from("Pricelet", "brunocardoso.fatec.itu@gmail.com")
					.to("C. Cane", user.getEmail()).withSubject(RECOVER.getSubject())
					.withPlainText(RECOVER.getBody() + " : " + user.getPassword()).buildEmail();
			getMailer().sendMail(email);
		} catch (NoResultException nre) {
			recovered = false;
		} catch (Exception e) {

		}
		return recovered;

	}

	private Mailer getMailer() {
		Mailer mailer = MailerBuilder
				.withSMTPServer("smtp.gmail.com", 587, "brunocardoso.fatec.itu@gmail.com", "Genipabu_0156")
				.withTransportStrategy(TransportStrategy.SMTP_TLS)
				// .withProxy("socksproxy.host.com", 1080, "proxy user", "proxy
				// password")
				.withSessionTimeout(10 * 1000).clearEmailAddressCriteria() // turns
																			// off
																			// email
																			// validation
				// .withProxy("mail.smtp.sendpartial", true)
				.withDebugLogging(true).buildMailer();

		return mailer;
	}
}
