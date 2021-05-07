package tn.fynova.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service(value="emailService")
public class SendEmailService {
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendEmail(String body, String topic) {
		
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("kingfedi1@gmail.com");
		simpleMailMessage.setTo("kingfedi1@gmail.com");
		simpleMailMessage.setSubject(topic);
		simpleMailMessage.setText(body);
		javaMailSender.send(simpleMailMessage);
	}
}
