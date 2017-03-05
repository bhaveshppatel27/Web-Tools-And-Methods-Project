package com.cinema.Addons;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.cinema.pojo.Customer;

public class EmailSender {
	
	public static void sendEmail(Customer customer){
	
		
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("ilovedc27@gmail.com", "8888801313"));
		email.setSSL(true);
		try {
			email.setFrom("ilovedc27@gmail.com");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		email.setSubject("Welcome to Cinema Docket");
		try {
			email.setMsg("Hello " + customer.getFirstName()+ " " + customer.getLastName() + ", \n\n Thank you for registering with Cinema Docket. \n Username: " +customer.getUsername()+ "\n Password: " +customer.getPassword()+" \n\n Thanks, \nCinema Docket Team");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			email.addTo(customer.getEmail());
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			email.send();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
