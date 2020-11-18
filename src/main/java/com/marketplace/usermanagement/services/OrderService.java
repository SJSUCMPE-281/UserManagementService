package com.marketplace.usermanagement.services;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.marketplace.usermanagement.models.Sale;
import com.marketplace.usermanagement.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

	@Autowired
	DynamoDBMapper dynamoDBMapper;

	public User getUser(String id){
		return dynamoDBMapper.load(User.class,id);
	}
	
}
