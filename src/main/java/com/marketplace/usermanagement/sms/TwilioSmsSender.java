package com.marketplace.usermanagement.sms;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketplace.usermanagement.config.TwilioConfiguration;
import com.marketplace.usermanagement.listeners.OrderListener;
import com.marketplace.usermanagement.models.Sale;
import com.marketplace.usermanagement.services.OrderService;

@Service("twilio")
public class TwilioSmsSender implements SmsSender {
	 private static final Logger LOGGER = LoggerFactory.getLogger(TwilioSmsSender.class);

	    private final TwilioConfiguration twilioConfiguration;
	    
	    @Autowired
	    public TwilioSmsSender(TwilioConfiguration twilioConfiguration) {
	        this.twilioConfiguration = twilioConfiguration;
	    }
	    @Autowired
	    OrderListener orderListener;
	    
	    @Override
	    public void sendSms(Sale sale) {
	    	
	    	System.out.println(sale);
	    	System.out.println(sale.getTotalAmount());
	    	System.out.println(sale.getTotalPrice());
	    	System.out.println(sale.getStatus());
	    	System.out.println(sale.getBuyer().getPhoneNumber());
	    	System.out.println(sale.getBuyer().getFirstName());
	    	System.out.println(sale.getBuyer().getLastName());
	    	System.out.println(sale.getBuyer().getRole());
	    	
	        if (isPhoneNumberValid(sale.getBuyer().getPhoneNumber())) {
	        	Sale saledetails = new Sale();
	            PhoneNumber to = new PhoneNumber(sale.getBuyer().getPhoneNumber());
	            PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
	            String message = createMessage(saledetails);
	            MessageCreator creator = Message.creator(to, from, message);
	            creator.create();
	            LOGGER.info("Send sms {}", sale.getBuyer());
	        } else {
	            throw new IllegalArgumentException(
	                    "Phone number [" + sale.getBuyer().getPhoneNumber() + "] is not a valid number"
	            );
	        }
	    }

	    private String createMessage(Sale sale) {
	    	
	    	String message = "";
	    	String firstName = sale.getBuyer().getFirstName();
	    	String lastName = sale.getBuyer().getLastName();
	    	String email = sale.getBuyer().getEmail();
	    	BigDecimal totalAmount = sale.getTotalAmount();
	    	/*if(sale.getBuyer().getRole().equals("Seller")) {
	    		message = "Hi" + firstName + lastName + "Your Product is added Successfully. "
	    				+ "Please see your mail"  + email + "for details.";
	    	}*/
	    	 if(sale.getBuyer().getRole().equals("Buyer")) {
	    		message = "Hi" + firstName + lastName + "Your Order is placed Successfully. "
	    				+ "Total Amount charged is =" + totalAmount 
	    				+ "Please see your mail" + email + "for details.";
	    	}  	
			return message;
		}

		private boolean isPhoneNumberValid(String phoneNumber) {
	        // TODO: Implement phone number validator
	        return true;
	    }
	}