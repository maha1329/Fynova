package tn.fynova.spring.service;

import org.springframework.mail.javamail.JavaMailSender;

public interface IMailingService {
	JavaMailSender getJavaMailSender();

}
