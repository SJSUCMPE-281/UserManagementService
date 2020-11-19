package com.marketplace.usermanagement.services;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.marketplace.usermanagement.models.PaymentRequest;
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
	
    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

	public Charge charge(PaymentRequest chargeRequest) throws StripeException {
		Map<String, Object> chargeParams = new HashMap<>();
		chargeParams.put("amount", chargeRequest.getAmount());
		chargeParams.put("currency", "USD");
		chargeParams.put("source", chargeRequest.getToken().getId());
		Charge charge = Charge.create(chargeParams);
		return charge;
	}

}
