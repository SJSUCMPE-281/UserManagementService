package com.marketplace.usermanagement.services;

import com.marketplace.usermanagement.models.Sale;
import com.marketplace.usermanagement.models.User;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Value("${twilio.account.id}")
    private String accountSid;
    @Value("${twilio.auth.token}")
    private String authToken;
    @Value("${trial_number}")
    private String trialNumber;

    public SmsService() {
        Twilio.init(accountSid,authToken);
    }

    public void sendTextMessage(Sale sale, User user) {
            String phone = user.getPhoneNumber();
            String message = getMessageContent(sale,user);
            Message.creator(
                    //phone will go here
                    new PhoneNumber("+19546817052"),
                    new PhoneNumber(trialNumber),getMessageContent(sale,user)
                    )
                    .create();
    }

    public String getMessageContent(Sale sale, User user) {
        String message = " ";
        if(user.getRole().equals("Seller")){
            message = "To seller : Sample message twilio using Spring";
        }else if(user.getRole().equals("Buyer")){
            message = "To buyer: Sample message twilio using Spring";
        }
        return message;
    }
}