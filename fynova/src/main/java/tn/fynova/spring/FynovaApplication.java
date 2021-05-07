package tn.fynova.spring;



import java.util.EnumSet;

import javax.faces.webapp.FacesServlet;
import javax.servlet.DispatcherType;

import org.ocpsoft.rewrite.servlet.RewriteFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import tn.fynova.spring.service.SendEmailService;


@SpringBootApplication
@EnableAutoConfiguration
public class FynovaApplication {
	
	@Autowired 
	SendEmailService sendEmailService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(FynovaApplication.class, args);
	}
	
	/*@EventListener(ApplicationReadyEvent.class)
	public void  triggerWhenstarts() {
		sendEmailService.sendEmail("testing email", "Tests");

	}*/
	
	@Bean
	public ServletRegistrationBean<FacesServlet> servletRegistrationBean() {
	FacesServlet servlet = new FacesServlet();
	return new ServletRegistrationBean<FacesServlet>(servlet,"*.jsf"); }

	@Bean
	public FilterRegistrationBean<RewriteFilter> rewriteFilter() {
	FilterRegistrationBean<RewriteFilter> rwFilter = new FilterRegistrationBean<RewriteFilter>(new RewriteFilter());
	rwFilter.setDispatcherTypes(EnumSet.of(DispatcherType.FORWARD, DispatcherType.REQUEST, DispatcherType.ASYNC, DispatcherType.ERROR));
	rwFilter.addUrlPatterns("/*");
	return rwFilter;}
	
	

}
