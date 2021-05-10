package tn.fynova.spring.control;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import tn.fynova.spring.entities.Credit;
import tn.fynova.spring.entities.Operation;
import tn.fynova.spring.entities.Project;
import tn.fynova.spring.entities.Transaction;
import tn.fynova.spring.entities.User;
import tn.fynova.spring.repository.CreditRepository;
import tn.fynova.spring.repository.ProjectRepository;
import tn.fynova.spring.repository.UserRepository;
import tn.fynova.spring.service.CreditServiceImpl;
import tn.fynova.spring.service.ICreditService;
import tn.fynova.spring.service.ITransactionService;
import tn.fynova.spring.service.TransactionExcelExporter;
import tn.fynova.spring.service.TransactionServiceImpl;

@RestController
@RequestMapping("/transaction")
public class TransactionRestController {

	@Autowired
	ITransactionService transService;

	@Autowired
	ICreditService creditService;

	@Autowired
	CreditRepository creditRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	TwilioController twilioCon;

	@GetMapping("/all")
	@ResponseBody
	public List<Transaction> getTransaction() {

		List<Transaction> list = transService.retrieveAllTransactions();
		return list;
	}

	@GetMapping("/all/{id}")
	@ResponseBody
	public Transaction getTransaction(@PathVariable("id") int id) {
		Transaction t = transService.retrieveTransactions(id);
		return t;

	}
	
	

	// Conversion
	public Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
		return java.sql.Timestamp.valueOf(dateToConvert);
	}

	// as an investor
	@PostMapping("/GiveMoney/{idCredit}/{nbreC}/{amount}")
	@ResponseBody
	public Boolean payWithCoupon(@PathVariable("idCredit") int idCredit, @PathVariable("nbreC") int nbreC,
			@PathVariable("amount") float amount) {
		Date d = convertToDateViaSqlTimestamp(LocalDateTime.now());
		float sum = amount * nbreC;
		Optional<Credit> CreditOptional = creditRepository.findById(idCredit);
		Credit c = CreditOptional.get();
		Transaction t = new Transaction(d, sum, Operation.debit);
		t.setTransaction_credit(c);
		t.setNbreC(nbreC);
		t.setAmountC(amount);
		c.setCredit_amount(sum);
		creditService.addCredit(c);
		transService.addTransaction(t);
	//	twilioCon.sendotp("+216" + c.getCredit_account().getAccount_user().getUser_phone());
		return true;
	}

	/*//as an investor , want my money 
	@PostMapping("/Demand/{why}/{nbreC}/{amount}")
	@ResponseBody
	public Boolean demandRetrait(@PathVariable("why") String why, @PathVariable("nbreC") int nbreC,
			@PathVariable("amount") float amount) {
		Date d = convertToDateViaSqlTimestamp(LocalDateTime.now());
		float sum = amount * nbreC;
		Optional<Credit> CreditOptional = creditRepository.findById(idCredit);
		Credit c = CreditOptional.get();
		Transaction t = new Transaction(d, sum, Operation.debit);
		t.setTransaction_credit(c);
		t.setNbreC(nbreC);
		t.setAmountC(amount);
		c.setCredit_amount(sum);
		creditService.addCredit(c);
		transService.addTransaction(t);
		twilioCon.sendotp("+216" + c.getCredit_account().getAccount_user().getUser_phone());
		return true;
	}*/
	
	// as an employee or an administrator
	// GetAllTransaction
	@GetMapping("/allExcel")
	public List<Transaction> exportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=transactionsAll.xlsx";
		response.setHeader(headerKey, headerValue);

		List<Transaction> listTrans = transService.listAll();

		TransactionExcelExporter excelExporter = new TransactionExcelExporter(listTrans);

		//excelExporter.export(response);
		return listTrans;
	}

	// Get All Transaction For specific User
	@GetMapping("/allExcelU/{UserID}")
	public List<Transaction> exportToExcelByUser(HttpServletResponse response, @PathVariable("UserID") int userId)
			throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=transactionsByUser.xlsx";
		response.setHeader(headerKey, headerValue);

		List<Transaction> listTrans = transService.listTransactionByUserId(userId);

		TransactionExcelExporter excelExporter = new TransactionExcelExporter(listTrans);

	//	excelExporter.export(response);
		return listTrans;
	}

	// Get All Transaction For specific Year
	@GetMapping("/allExcelY/{annee}")
	public String exportToExcelByYear(HttpServletResponse response, @PathVariable("annee") int annee)
			throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=transactionsByYear.xlsx";
		response.setHeader(headerKey, headerValue);

		List<Transaction> listTrans = transService.listTransactionByDate(annee);

		TransactionExcelExporter excelExporter = new TransactionExcelExporter(listTrans);

		// String res=excelExporter.export(response);

		return "successful generation of transactionsByYear.xlsx";
	}

	// Get statistic Months / amount BY year
	@GetMapping("/statistic/{year}")
	public String statisticMonthByAmount(@PathVariable("year") int year) throws IOException {
		Map<Double, Double> MA = new HashMap<Double, Double>();
	    MA = transService.StatisticMonthbyAmount(year);	
		String json = new ObjectMapper().writeValueAsString(MA);
		return json;
		
	}

	

}
