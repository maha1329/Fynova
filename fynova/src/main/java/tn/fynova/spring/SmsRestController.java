package tn.fynova.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.fynova.spring.entities.User;
import tn.fynova.spring.service.UserServiceImpl;

@RestController
@RequestMapping("/api/sms")
public class SmsRestController {
	private final Service service;
	 
	@Autowired
	UserServiceImpl userserviceimpl;
	
	 @Autowired
	    public SmsRestController(Service service) {
	        this.service = service;
	    }
    @PostMapping
    public void sendSms(@RequestParam("phoneNumber") String phoneNumber, @RequestParam("message") String message) {
        service.sendSms( phoneNumber,  message);
    }
    
    
    @PostMapping("/AccCreated1")
    public void sendSmsAccountCreated(@RequestParam("phoneNumber") String phoneNumber) {
        service.sendSms( phoneNumber,  "Welcome to Fynova, Your account is successfully created !");
    }
    
    @PostMapping("/AccCreated")
    public void sendSmsAccountCreated(@RequestBody User user) {
        service.sendSms(user.getUser_phone(),  "Welcome to Fynova, Your account is successfully created !");
    }
    
    
    @PostMapping("/AccountStatus1")
    public void sendSmsCriticStatus(@RequestParam("phoneNumber") String phoneNumber) {
        service.sendSms( phoneNumber,  "Warning, your account has reached critic status !");
    }
    @PostMapping("/AccountStatus")
    public void sendSmsCriticStatus(@RequestBody User user) {
        service.sendSms( user.getUser_phone(),  "Warning, your account has reached critic status !");
    }
    
    
    @PostMapping("/ProjectInfo1")
    public void sendSmsProjectInfo(@RequestParam("phoneNumber") String phoneNumber) {
        service.sendSms( phoneNumber,  "Your project is 79% financed !");
    }
    
    @PostMapping("/ProjectInfo")
    public void sendSmsProjectInfo(@RequestBody User user) {
        service.sendSms( user.getUser_phone(),  "Your project is " + user.getProject_progress()+" financed !");
    }
    
}
