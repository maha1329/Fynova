package tn.fynova.spring.control;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("twilio")
public class Twilioproperties {

	public static final String accountSid = "AC25d4b0b03bba57c4aaaa83bf4af037c2";
	public static final String authToken = "0493b3b42bb788ea501af630df8ca43e";
	public static final String serviceId = "VA28529666a07574a4d964a17dedda010f";

	public Twilioproperties() {

	}

	public String getAccountSid() {
		return accountSid;
	}

	public String getAuthToken() {
		return authToken;
	}

	public String getServiceId() {
		return serviceId;
	}

}
