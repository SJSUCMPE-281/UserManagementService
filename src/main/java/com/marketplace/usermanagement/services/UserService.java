package com.marketplace.usermanagement.services;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.marketplace.usermanagement.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public User save(User user){
        dynamoDBMapper.save(user);
        return user;
    }
}
