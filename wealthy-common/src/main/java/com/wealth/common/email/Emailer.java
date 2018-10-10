package com.wealth.common.email;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.config.TransportStrategy;


public class Emailer {
	//TODO:put values in a property file
	private final static String EMAIL_FROM  =  "brunocardoso.fatec.itu@gmail.com";
	private final static String EMAIL_FROM_NAME = "Pricelet";
	private final static int EMAIL_STPM_PORT = 587;
	private final static String EMAIL_STPM_PASSWORD = "Genipabu_0156";
	private final static String EMAIL_STPM_SERVICE = "smtp.gmail.com";
	
	public static void sendEmail(Emailable emailable){
		Email email =
				 EmailBuilder.startingBlank()
				.from(EMAIL_FROM_NAME, EMAIL_FROM)
				.to(emailable.getEmailReceiver())
				.withSubject(emailable.getEmailType().getSubject())
				.withPlainText(emailable.getBody())
				.buildEmail();
		getDefautMailer().sendMail(email);
	}
	
	private static Mailer getDefautMailer() {
		Mailer mailer = MailerBuilder
				.withSMTPServer(EMAIL_STPM_SERVICE, 
								 EMAIL_STPM_PORT, 
								 EMAIL_FROM,
								 EMAIL_STPM_PASSWORD)
				.withTransportStrategy(TransportStrategy.SMTP_TLS)
				.withSessionTimeout(10 * 1000).clearEmailAddressCriteria() 
				// .withProxy("mail.smtp.sendpartial", true)
				.withDebugLogging(true).buildMailer();
		return mailer;
	}
}
