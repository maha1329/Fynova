package tn.fynova.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("sms")
public class TwilioConfiguration {

    private String accountSid="ACaf7881cde0cfc0a7b6ba15adb9dd623e";
    private String authToken="a73a3212a5bcf510664d7e2076485945";
    private String trialNumber="+12676338043";

    public TwilioConfiguration() {

    }

    public String getAccountSid() {
        return accountSid;
    }

    public void setAccountSid(String accountSid) {
        this.accountSid = accountSid;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getTrialNumber() {
        return trialNumber;
    }

    public void setTrialNumber(String trialNumber) {
        this.trialNumber = trialNumber;
    }
}
