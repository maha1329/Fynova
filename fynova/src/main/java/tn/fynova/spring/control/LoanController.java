package tn.fynova.spring.control;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lowagie.text.DocumentException;

import tn.fynova.spring.entities.Account;
import tn.fynova.spring.entities.Credit;
import tn.fynova.spring.entities.LogCredit;
import tn.fynova.spring.entities.Operation;
import tn.fynova.spring.entities.Transaction;
import tn.fynova.spring.service.ICreditService;
import tn.fynova.spring.service.ILogCreditService;
import tn.fynova.spring.service.SimulationPDFExporter;

import javax.servlet.http.HttpServletResponse;

@Controller(value = "LoanController") // Name of the bean in Spring IoC
@ELBeanName(value = "LoanController") // Nam
public class LoanController {

	@Autowired
	ICreditService creditService;

	@Autowired
	ILogCreditService logcreditService;

	private float amount;
	private float interest;
	
	private String resultat;
	
	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getInterest() {
		return interest;
	}

	public void setInterest(float interest) {
		this.interest = interest;
	}

	// Conversion
	public Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
		return java.sql.Timestamp.valueOf(dateToConvert);
	}

	
	// Simulation du Credit
	
	public String si(/*float amount,float interest*/)  throws DocumentException, IOException {
		
		List<String> l = creditService.contrats(1000, 10);
		resultat = new ObjectMapper().writeValueAsString(l);
		System.out.print(resultat);
		return 	resultat;
	}

	/*
	 * @GetMapping("/Text") public String pDFToString() { String text =
	 * creditService.extractToPDF("C:\\Users\\FATHALLAH\\Documents\\lettre.pdf");
	 * return text; }
	 * 
	 * // Spring scheduler payment of bills
	 * 
	 * //@Scheduled(cron = "0/5 * * * * ?")
	 * 
	 * @PostMapping("/paybillsAuto")
	 * 
	 * @ResponseBody public void paybills() { Date date =
	 * convertToDateViaSqlTimestamp(LocalDateTime.now());
	 * creditService.PaymentDevis(1, 10); LogCredit L = new LogCredit();
	 * L.setCredit(creditService.retrieveCredit(1)); L.setDate(date);
	 * L.setInterestRate(creditService.retrieveCredit(1).getCredit_interestRate());
	 * logcreditService.addLogCredit(L); }
	 * 
	 * @PostMapping("/AddMoney/{}")
	 * 
	 * @ResponseBody public void AddMoney(@PathVariable("id") int id) { //
	 * logcreditService; }
	 */

}
