package com.marketplace.usermanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marketplace.usermanagement.models.Sale;
import com.marketplace.usermanagement.services.SmsService;

@CrossOrigin
@RestController
@RequestMapping("api/v1/sms")
public class SmsController {

    @Autowired
	SmsService smsService;

//	@PostMapping
//	public void sendSms(){
//		smsService.sendSMS();
//	}
}