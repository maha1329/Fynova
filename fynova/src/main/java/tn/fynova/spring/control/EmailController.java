package tn.fynova.spring.control;

import javax.faces.application.FacesMessage;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.context.PrimeFacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.fynova.spring.service.SendEmailService;

@Scope(value="session")
@Controller(value="emailController")
@ELBeanName(value="emailController")
public class EmailController {
	@Autowired
	SendEmailService sendEmailService;
	String body;
	String topic;
	
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public void send() {
		sendEmailService.sendEmail(body, topic);
	}

}
