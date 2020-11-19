package com.marketplace.usermanagement.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketplace.usermanagement.models.Sale;
import com.marketplace.usermanagement.models.User;
import com.marketplace.usermanagement.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    @Autowired
    private EmailService emailService;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping
    public void sendEmail() throws IOException {
        User buyer = objectMapper.readValue(new URL("file:src/main/java/com/marketplace/usermanagement/staticData/userBuyer.json"), User.class);
        User seller = objectMapper.readValue(new URL("file:src/main/java/com/marketplace/usermanagement/staticData/userSeller.json"), User.class);
        Sale sale = objectMapper.readValue(new URL("file:src/main/java/com/marketplace/usermanagement/staticData/Sale.json"), Sale.class);
        emailService.sendHTML(sale, buyer);
    }
}
