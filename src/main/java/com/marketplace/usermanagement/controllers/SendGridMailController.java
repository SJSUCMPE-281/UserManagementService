package com.marketplace.usermanagement.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marketplace.usermanagement.models.Sale;
import com.marketplace.usermanagement.models.User;
import com.marketplace.usermanagement.services.EmailService;

@CrossOrigin
@RestController
@RequestMapping("mail/send")
public class SendGridMailController {
	
	private final EmailService emailService;

	@Autowired
    public SendGridMailController(EmailService emailService) {
        this.emailService = emailService;
    }
	
	@PostMapping
	public void sendEmail(@RequestBody Sale sale) throws IOException {
		emailService.sendEmail(sale);
	}
}
