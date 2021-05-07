package tn.fynova.spring.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.twilio.Twilio;

@Configuration
public class TwilioInitiazer {

	private final static Logger LOGGER = LoggerFactory.getLogger(TwilioInitiazer.class);

	private final Twilioproperties twilioproperties;

	@Autowired
	public TwilioInitiazer(Twilioproperties twilioproperties) {
		this.twilioproperties = twilioproperties;
		Twilio.init(twilioproperties.getAccountSid(), twilioproperties.getAuthToken());
		LOGGER.info("Twilio initialized ... with account sid {} ", twilioproperties.getAccountSid());

	}

}
