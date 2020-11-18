package com.marketplace.usermanagement.listeners;

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

	 static final String QUEUE_NAME = "marketplace-order-save-queue";

	    @SqsListener(QUEUE_NAME)
	    public void receiveMessage(String message) throws JsonProcessingException {
	         Sale sale = objectMapper.readValue(message,Sale.class);
	         orderService.fetchdetails(sale);
	         System.out.println(message);
	         System.out.println("********************************************");
	         System.out.println(sale);
	    }
}
