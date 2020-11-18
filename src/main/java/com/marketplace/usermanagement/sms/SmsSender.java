package com.marketplace.usermanagement.sms;

import com.marketplace.usermanagement.models.Sale;
import com.marketplace.usermanagement.models.User;

public interface SmsSender {

    void sendSms(Sale sale);

    // or maybe void sendSms(String phoneNumber, String message);
}