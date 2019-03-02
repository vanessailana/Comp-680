package com.comp680.backend.controllers;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

class Support extends Object {
	String email;
	String message;
};

@RestController
public class SupportController {
	@Autowired
	private JavaMailSender sender;

	@PostMapping(value = "/support/{email}")
	public String sendEmail(@RequestBody Support request) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			InternetAddress emailAddress = new InternetAddress(request.email);
			emailAddress.validate();
		} catch(AddressException e) {
			return "Invalid email format.";
		}
  
        try {
			helper.setTo(request.email);
			System.out.println(request.email);
            helper.setText(request.email);
            helper.setSubject("Mail From Customer Support");
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Error occurred while sending mail.";
        }
        sender.send(message);
        return "Email successfully sent!";
	}
}

