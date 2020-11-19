package com.marketplace.usermanagement.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketplace.usermanagement.models.OrderDetails;
import com.marketplace.usermanagement.models.Sale;
import com.marketplace.usermanagement.models.User;
import com.sendgrid.SendGrid;

@Service
public class EmailService {

	private SendGrid sendGrid;

	@Autowired
	public EmailService(SendGrid sendGrid) {
		this.sendGrid = sendGrid;
	}

	public void sendHTML(Sale sale, User user) {
		String fromEmail = "anisha.agarwal@sjsu.edu";
		String toEmail = user.getEmail();
		String subject = "Order has been placed for "+sale.getTotalAmount();
		String body = "Order has been placed with order id: "+sale.getOrderId();
		Response response = sendEmail(fromEmail, toEmail, subject, new Content("text/html", body));
		System.out.println("Status Code: " + response.getStatusCode() + ", Body: " + body + ", Headers: "
				+ response.getHeaders());
	}

	private Response sendEmail(String from, String to, String subject, Content content) {
		Mail mail = new Mail(new Email(from), subject, new Email(to), content);
		//mail.setReplyTo(new Email("jeenathampi.23@gmail.com"));
		Request request = new Request();
		Response response = null;
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			response = this.sendGrid.api(request);
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		return response;
	}

//	public String createContentForSeller(User user, Sale sale) {
//		String productDetails = "";
//		String firstName = user.getFirstName();
//    	String lastName = user.getLastName();
//    	BigDecimal totalAmount = sale.getTotalAmount();
//    	List<OrderDetails> orderDetails = sale.getOrderDetailsDTO();
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
//		String message = "Hi" + firstName + lastName + "Your Product has been added Successfully. Please find the details"
//				+ productDetails;
//		return message;
//	}
	
}
