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
//https://mysterious-harbor-56923.herokuapp.com
@EnableWebMvc
@Controller
@CrossOrigin(origins = "http://localhost:4200" , maxAge = 3600 )
public class SupportsController {
	@Autowired
	private JavaMailSender sender;
	
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

		try {
			InternetAddress emailAddress = new InternetAddress(request.email);
			emailAddress.validate();
		} catch(AddressException e) {
			obj.put("message", "Invalid email format.");
			return new ResponseEntity<>(obj, HttpStatus.UNPROCESSABLE_ENTITY);	
		}
  
        try {
			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);
	
			helper.setTo(request.email);
            helper.setText(request.message);
			helper.setSubject("Mail From Customer Support");

			sender.send(message);
			obj.put("message", "Email successfully sent!");
			return new ResponseEntity<>(obj, HttpStatus.CREATED);
        } catch (MessagingException e) {
			e.printStackTrace();
			obj.put("message", "Error occurred while sending mail.");
			return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
}
