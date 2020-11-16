package com.marketplace.usermanagement.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketplace.usermanagement.models.User;
import com.marketplace.usermanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationListener {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserService userService;

    static final String QUEUE_NAME = "marketplace-user-registration-queue";

    @SqsListener(QUEUE_NAME)
    public void receiveMessage(String message) throws JsonProcessingException {
         User user = objectMapper.readValue(message,User.class);
         userService.save(user);
        System.out.println(message);
    }
}


