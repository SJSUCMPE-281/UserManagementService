package com.marketplace.usermanagement.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketplace.usermanagement.listeners.OrderListener;
import com.marketplace.usermanagement.models.OrderDetails;
import com.marketplace.usermanagement.models.Sale;
import com.marketplace.usermanagement.models.User;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service
public class EmailService {

	@Autowired
	SendGrid sendGrid;
	
//	public void sendEmail(Sale sale) throws IOException {
//
//		Request request = new Request();
//
//		/*if(user.getRole().equals("Seller")) {
//
//			Email from = new Email("anisha.agarwal@sjsu.edu");
//			Email to = new Email(user.getEmail());
//			String subject = "Product added Successfully";
//			String message = createContentForSeller(user, sale);
//			Content content = new Content("text/plain", message);
//			Mail mail = new Mail(from, subject, to, content);
//			request.setBody(mail.build());
//			Response response = sendGrid.api(request);
//		}*/
//		 if(sale.getBuyer().getRole().equals("Buyer")) {
//
//			Email from = new Email("anisha.agarwal@sjsu.edu");
//			Email to = new Email(sale.getBuyer().getEmail());
//			String subject = "Order placed Successfully";
//			String message = createContentForBuyer(sale);
//			Content content = new Content("text/plain", message);
//			Mail mail = new Mail(from, subject, to, content);
//			request.setBody(mail.build());
//			Response response = sendGrid.api(request);
//		}
//	}
	
//	public String createContentForBuyer(Sale sale) {
//
//		String productDetails = "";
//		String firstName = sale.getBuyer().getFirstName();
//    	String lastName = sale.getBuyer().getLastName();
//    	String phoneNumber = sale.getBuyer().getPhoneNumber();
//    	String status = sale.getStatus();
//    	String trackingId = sale.getTrackingId();
//    	BigDecimal totalAmount = sale.getTotalAmount();
//    	List<OrderDetails> orderDetails = sale.getOrderDetails();
//    	for(OrderDetails order : orderDetails) {
//    		String productName = order.getProductName();
//    		String productDescription = order.getProductDescription();
//    		BigDecimal productPrice = order.getPrice();
//    		String shopName = order.getShopName();
//    		String URL = order.getImageUrl();
//    		String category = order.getCategory();
//    		int quantity = order.getQuantity();
//    		productDetails = "Product =" + productName + "Description =" + productDescription + "Price =" + productPrice
//    				+ "Shop Name = " + shopName + "Image = " + URL + "Product Category =" + category + "Quantity =" + quantity;
//    	}
//		String message = "Hi" + firstName + lastName + "Your Order has been placed Successfully. Please find the details"
//				+ productDetails + "Order Status = " + status + "Tracking ID =" + trackingId + "Total Amount of Order =" + totalAmount;
//		return message;
//
//	}
	public String createContentForSeller(User user, Sale sale) {
		String productDetails = "";
		String firstName = user.getFirstName();
    	String lastName = user.getLastName();
    	BigDecimal totalAmount = sale.getTotalAmount();
    	List<OrderDetails> orderDetails = sale.getOrderDetails();
    	for(OrderDetails order : orderDetails) {
    		String productName = order.getProductName();
    		String productDescription = order.getProductDescription();
    		BigDecimal productPrice = order.getPrice();
    		String shopName = order.getShopName();
    		String URL = order.getImageUrl();
    		String category = order.getCategory();
    		int quantity = order.getQuantity();
    		productDetails = "Product =" + productName + "Description =" + productDescription + "Price =" + productPrice
    				+ "Shop Name = " + shopName + "Image = " + URL + "Product Category =" + category + "Quantity =" + quantity;
    	}
		String message = "Hi" + firstName + lastName + "Your Product has been added Successfully. Please find the details" 
				+ productDetails;
		return message;
	}
	
}
