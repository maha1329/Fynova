package tn.fynova.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import tn.fynova.spring.service.ISmsSender;
import tn.fynova.spring.service.TwilioSmsSender;

@org.springframework.stereotype.Service
public class Service {

    private final TwilioSmsSender smsSender;

    @Autowired
    public Service(@Qualifier("sms") TwilioSmsSender smsSender) {
        this.smsSender = smsSender;
    }

    public void sendSms(String phoneNumber, String message) {
        smsSender.sendSms( phoneNumber,  message);
    }
}
