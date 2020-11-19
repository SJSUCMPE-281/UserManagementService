package com.marketplace.usermanagement.controllers;

import com.marketplace.usermanagement.models.PaymentRequest;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marketplace.usermanagement.services.PaymentService;
import com.stripe.exception.StripeException;

@RestController
@CrossOrigin
@RequestMapping("/api/payment")
public class PaymentController {

	private PaymentService paymentService;

	@Autowired
	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@PostMapping
	public String completePayment(@RequestBody PaymentRequest request) throws StripeException {
		Charge charge =  paymentService.charge(request);
		return  charge.toJson();
	}
}