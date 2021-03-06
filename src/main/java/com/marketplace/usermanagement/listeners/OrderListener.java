package com.marketplace.usermanagement.listeners;

import com.marketplace.usermanagement.models.User;
import com.marketplace.usermanagement.services.EmailService;
import com.marketplace.usermanagement.services.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketplace.usermanagement.models.Sale;
import com.marketplace.usermanagement.services.OrderService;
@Service
public class OrderListener {
	
	@Autowired
	ObjectMapper objectMapper;
	 
	@Autowired
	OrderService orderService;

	@Autowired
	SmsService smsService;

	@Autowired
	EmailService emailService;

	static final String QUEUE_NAME = "marketplace-order-save-queue";

	@SqsListener(QUEUE_NAME)
	public void receiveMessage(String message) throws JsonProcessingException {
		System.out.println(message);
		Sale sale = objectMapper.readValue(message,Sale.class);
		String buyerId = sale.getBuyerId();
		User buyer = orderService.getUser(buyerId);
		String sellerId = sale.getOrderDetailsDTO().get(0).getSellerId();
		User seller = orderService.getUser(sellerId);
		//Send e-email and text message to both seller and buyer
		smsService.sendTextMessage(sale,buyer);
		smsService.sendTextMessage(sale,seller);
		emailService.sendHTML(sale,buyer);
		emailService.sendHTML(sale,seller);
	}
}
