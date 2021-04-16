package tn.fynova.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sms")
public class SmsRestController {
	private final Service service;
	 
	 @Autowired
	    public SmsRestController(Service service) {
	        this.service = service;
	    }
    @PostMapping
    public void sendSms(@RequestParam("phoneNumber") String phoneNumber, @RequestParam("message") String message) {
        service.sendSms( phoneNumber,  message);
    }
    
    
    @PostMapping("/AccCreated")
    public void sendSmsAccountCreated(@RequestParam("phoneNumber") String phoneNumber) {
        service.sendSms( phoneNumber,  "Welcome to Fynova, Your account is successfully created");
    }
}
