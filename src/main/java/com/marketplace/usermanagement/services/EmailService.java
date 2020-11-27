package com.marketplace.usermanagement.services;

import java.io.IOException;

import com.marketplace.usermanagement.mappers.OrderMapper;
import com.marketplace.usermanagement.models.Order;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.marketplace.usermanagement.models.Sale;
import com.marketplace.usermanagement.models.User;
import com.sendgrid.SendGrid;

@Service
@Slf4j
public class EmailService {

	@Value("${sendgrid.templateid.key}")
	private String templateId;

	private SendGrid sendGrid;

	private OrderMapper orderMapper;

	private final String FROM_ADDRESS = "jeenathampi1023@gmail.com";

	@Autowired
	public EmailService(SendGrid sendGrid, OrderMapper orderMapper) {
		this.sendGrid = sendGrid;
		this.orderMapper = orderMapper;
	}

	public void sendHTML(Sale sale, User user) {
		Order newOrder = orderMapper.toOrder(sale);
		Response response = sendEmail(newOrder, user);
	}

	private Response sendEmail(Order order, User user) {
		Mail mail = getMailData(order, user);
		Request request = new Request();
		Response response = null;
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			response = this.sendGrid.api(request);
		} catch (IOException ex) {
			log.info("Exception happened while sending mail, error={}",
					ex.getMessage());
		}
		return response;
	}

	private Mail getMailData(Order order, User user) {
		Mail mail = new Mail();
		Email from = new Email(FROM_ADDRESS);
		Email to = new Email(user.getEmail());
		mail.setFrom(from);
		Personalization personalization = new Personalization();
		personalization.addTo(to);
		personalization.addDynamicTemplateData("data", order);
		mail.setTemplateId(templateId);
		mail.addPersonalization(personalization);
		return mail;
	}

}
