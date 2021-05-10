package tn.fynova.spring.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
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

@Component
@RestController
@RequestMapping("/credit")
public class CreditController {

	@Autowired
	ICreditService creditService;

	@Autowired
	ILogCreditService logcreditService;

	// Conversion
	public Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
		return java.sql.Timestamp.valueOf(dateToConvert);
	}

	@GetMapping("/{id}")
	@ResponseBody
	public Credit getCredit(@PathVariable("id") int id) {
		Credit t= creditService.findCreditWithProject(1);	
		return t;

	}
	// Simulation du Credit
	@GetMapping("/export/pdf/{amount}/{interest}")
	public String exportToPDF(HttpServletResponse response, @PathVariable("amount") float amount,
			@PathVariable("interest") float interest) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		List<String> l = creditService.contrats(amount, interest);
		SimulationPDFExporter exporter = new SimulationPDFExporter(l);
		// exporter.export(response);
		String json = new ObjectMapper().writeValueAsString(l);
		return json;

	}

	@GetMapping("/Text")
	public String pDFToString() {
		String text = creditService.extractToPDF("C:\\Users\\FATHALLAH\\Documents\\lettre.pdf");
		return text;
	}

	// Spring scheduler payment of bills

	//@Scheduled(cron = "0/5 * * * * ?")
	@PostMapping("/paybillsAuto")
	@ResponseBody
	public void paybills() {
		Date date = convertToDateViaSqlTimestamp(LocalDateTime.now());
		creditService.PaymentDevis(1, 10);
		LogCredit L = new LogCredit();
		L.setCredit(creditService.retrieveCredit(1));
		L.setDate(date);
		L.setInterestRate(creditService.retrieveCredit(1).getCredit_interestRate());
		logcreditService.addLogCredit(L);
	}

	@PostMapping("/AddMoney/{}")
	@ResponseBody
	public void AddMoney(@PathVariable("id") int id) {
		// logcreditService;
	}

}
