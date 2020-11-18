package com.marketplace.usermanagement.services;

import com.marketplace.usermanagement.models.Sale;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

	public void fetchdetails(Sale sale) {
		System.out.println(sale.getOrderId());
		System.out.println(sale.getBuyer().getEmail());
		System.out.println(sale.getBuyer().getUserId());
		
		
		
		/*sale.setOrderId(sale.getOrderId());
		sale.setStatus(sale.getStatus());
		sale.setTaxAmount(sale.getTaxAmount());
		sale.setTotalAmount(sale.getTotalAmount());
		sale.setTotalPrice(sale.getTotalPrice());
		sale.setTrackingId(sale.getTrackingId());
		sale.setBuyer(sale.getBuyer());
		sale.getBuyer().setPhoneNumber(sale.getBuyer().getPhoneNumber());
		sale.setOrderDetails(sale.getOrderDetails());
		sale.setCreatedAt(sale.getCreatedAt());
		sale.setUpdatedAt(sale.getUpdatedAt());*/
	}
	
}
