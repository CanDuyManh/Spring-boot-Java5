package com.poly.controller;

import javax.mail.Address;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class sendMail  {
	@Autowired
	JavaMailSender sender;
	
	@RequestMapping("mail/send")
	public void send() {
		try {
			MimeMessage mess = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mess, true, "utf-8");
			helper.setFrom("Can duy manh <duchotboy9@gmail.com>");
			helper.setTo("manhcdph11373@fpt.edu.vn");
			helper.setSubject("test");
			helper.setText("dassdasdsadsa");
			sender.send(mess);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("sai");
		}
		
		
	}
	

}
