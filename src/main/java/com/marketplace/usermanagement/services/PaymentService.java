package com.marketplace.usermanagement.services;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.marketplace.usermanagement.listeners.OrderListener;
import com.marketplace.usermanagement.models.Sale;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;

@Service
public class PaymentService {

	@Value("${stripe.secret.key}")
	private String secretKey;
	
	public enum Currency{
		INR,USD;
	}
	private Token token;
	
    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }
	public String charge(Sale sale) throws StripeException {
		
		 Map<String, Object> chargeParams = new HashMap<>();
		 chargeParams.put("amount", sale.getTotalAmount());
		 chargeParams.put("email",sale.getBuyer().getEmail());
		 chargeParams.put("firstname", sale.getBuyer().getFirstName());
		 chargeParams.put("currency", Currency.USD);
		 chargeParams.put("source", token.getId());
		 
	    /* chargeParams.put("amount", chargeRequest.getAmount());
	     chargeParams.put("currency", PaymentRequest.Currency.USD);
	     chargeParams.put("source", chargeRequest.getToken().getId());*/
	     
		Charge charge = Charge.create(chargeParams);
		return charge.getId();
	}

}
