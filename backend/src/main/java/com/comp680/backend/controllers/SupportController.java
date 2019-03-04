package com.comp680.backend.controllers;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.comp680.backend.models.Support;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
@EnableWebMvc
@Controller
public class SupportController {
	@Autowired
	private JavaMailSender sender;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/support", method = RequestMethod.POST, consumes="application/json",produces = "application/json")
	public @ResponseBody ResponseEntity<?> sendEmail(@RequestBody Support request) {
		JSONObject obj = new JSONObject();

		if(request.message == null || request.message.length() == 0) {
			obj.put("message", "Cannot send an email with empty message body");
			return new ResponseEntity<>(obj, HttpStatus.BAD_REQUEST);
		}
		if( request.email == null || request.email.length() == 0) {
			obj.put("message", "Cannot send an email with empty email address");
			return new ResponseEntity<>(obj, HttpStatus.BAD_REQUEST);
		}

        MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		try {
			InternetAddress emailAddress = new InternetAddress(request.email);
			emailAddress.validate();
		} catch(AddressException e) {
			obj.put("message", "Invalid email format.");
			return new ResponseEntity<>(obj, HttpStatus.UNPROCESSABLE_ENTITY);	
		}
  
        try {
			helper.setTo(request.email);
            helper.setText(request.message);
            helper.setSubject("Mail From Customer Support");
        } catch (MessagingException e) {
			e.printStackTrace();
			obj.put("message", "Error occurred while sending mail.");
			return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        }
		sender.send(message);
		obj.put("message", "Email successfully sent!");
		return new ResponseEntity<>(obj, HttpStatus.CREATED);
	}
}
