package com.marketplace.usermanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.marketplace.usermanagement.models.Sale;
import com.marketplace.usermanagement.sms.SmsSender;
import com.marketplace.usermanagement.sms.TwilioSmsSender;

@org.springframework.stereotype.Service
public class SmsService {

    private final SmsSender smsSender;

    @Autowired
    public SmsService(@Qualifier("twilio") TwilioSmsSender smsSender) {
        this.smsSender = smsSender;
    }

    public void sendSms(Sale sale) {
        smsSender.sendSms(sale);
    }
}