package com.marketplace.usermanagement.controllers;

import com.marketplace.usermanagement.models.Sale;
import com.marketplace.usermanagement.models.User;
import com.marketplace.usermanagement.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    @Autowired
    private EmailService emailService;

    @GetMapping
    public void sendEmail() {
        User user = new User();
        //populate user details here
        Sale sale = new Sale();
        // populate sale details here
        emailService.sendHTML(sale, user);
    }
}
