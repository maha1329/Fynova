package tn.fynova.spring.control;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import tn.fynova.spring.entities.Credit;
import tn.fynova.spring.entities.Operation;
import tn.fynova.spring.entities.Transaction;
import tn.fynova.spring.repository.CreditRepository;
import tn.fynova.spring.repository.UserRepository;
import tn.fynova.spring.service.ICreditService;
import tn.fynova.spring.service.ITransactionService;
import tn.fynova.spring.service.TransactionExcelExporter;

@Controller(value = "TransactionController") // Name of the bean in Spring IoC
@ELBeanName(value = "TransactionController") // Name
public class TransactionController {

	private int transactionid;
	private Date transactionDate;

	private int idprojet;

	public int getIdprojet() {
		return idprojet;
	}

	public void setIdprojet(int idprojet) {
		this.idprojet = idprojet;
	}

	public int getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(int transactionid) {
		this.transactionid = transactionid;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

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

	private int idCredit;
	private int nbreC;
	private float amount;

	public ITransactionService getTransService() {
		return transService;
	}

	public void setTransService(ITransactionService transService) {
		this.transService = transService;
	}

	public ICreditService getCreditService() {
		return creditService;
	}

	public void setCreditService(ICreditService creditService) {
		this.creditService = creditService;
	}

	public CreditRepository getCreditRepository() {
		return creditRepository;
	}

	public void setCreditRepository(CreditRepository creditRepository) {
		this.creditRepository = creditRepository;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public TwilioController getTwilioCon() {
		return twilioCon;
	}

	public void setTwilioCon(TwilioController twilioCon) {
		this.twilioCon = twilioCon;
	}

	public int getIdCredit() {
		return idCredit;
	}

	public void setIdCredit(int id_credit) {
		this.idCredit = id_credit;
	}

	public int getNbreC() {
		return nbreC;
	}

	public void setNbreC(int nbreC) {
		this.nbreC = nbreC;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	private List<Transaction> trans;

	public List<Transaction> getTrans() {
		trans = transService.listAll();
		return trans;
	}

	public void setTrans(List<Transaction> trans) {
		this.trans = trans;
	}

	// Conversion
	public Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
		return java.sql.Timestamp.valueOf(dateToConvert);
	}

	// as an investor

	public void payWithCoupon() {
		Credit c=creditService.findCreditWithProject(idprojet);
		Date d = convertToDateViaSqlTimestamp(LocalDateTime.now());
		float sum = amount * nbreC;
		Transaction t = new Transaction(d, sum, Operation.debit);
		t.setTransaction_credit(c);
		t.setNbreC(nbreC);
		t.setAmountC(amount);
		c.setCredit_amount(sum);
		creditService.addCredit(c);
		transService.addTransaction(t);
		// twilioCon.sendotp("+216" +
		// c.getCredit_account().getAccount_user().getUser_phone());
		System.out.print(t.toString());
	}

	/*
	 * //as an investor , want my money
	 * 
	 * @PostMapping("/Demand/{why}/{nbreC}/{amount}")
	 * 
	 * @ResponseBody public Boolean demandRetrait(@PathVariable("why") String
	 * why, @PathVariable("nbreC") int nbreC,
	 * 
	 * @PathVariable("amount") float amount) { Date d =
	 * convertToDateViaSqlTimestamp(LocalDateTime.now()); float sum = amount *
	 * nbreC; Optional<Credit> CreditOptional = creditRepository.findById(idCredit);
	 * Credit c = CreditOptional.get(); Transaction t = new Transaction(d, sum,
	 * Operation.debit); t.setTransaction_credit(c); t.setNbreC(nbreC);
	 * t.setAmountC(amount); c.setCredit_amount(sum); creditService.addCredit(c);
	 * transService.addTransaction(t); twilioCon.sendotp("+216" +
	 * c.getCredit_account().getAccount_user().getUser_phone()); return true; }
	 */

	// as an employee or an administrator
	// GetAllTransaction
	/*
	 * @GetMapping("/allExcel") public List<Transaction>
	 * exportToExcel(HttpServletResponse response) throws IOException {
	 * response.setContentType("application/octet-stream"); DateFormat dateFormatter
	 * = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss"); String currentDateTime =
	 * dateFormatter.format(new Date());
	 * 
	 * String headerKey = "Content-Disposition"; String headerValue =
	 * "attachment; filename=transactionsAll.xlsx"; response.setHeader(headerKey,
	 * headerValue);
	 * 
	 * List<Transaction> listTrans = transService.listAll();
	 * 
	 * TransactionExcelExporter excelExporter = new
	 * TransactionExcelExporter(listTrans);
	 * 
	 * //excelExporter.export(response); return listTrans; }
	 * 
	 * // Get All Transaction For specific User
	 * 
	 * @GetMapping("/allExcelU/{UserID}") public List<Transaction>
	 * exportToExcelByUser(HttpServletResponse response, @PathVariable("UserID") int
	 * userId) throws IOException {
	 * response.setContentType("application/octet-stream"); DateFormat dateFormatter
	 * = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss"); String currentDateTime =
	 * dateFormatter.format(new Date());
	 * 
	 * String headerKey = "Content-Disposition"; String headerValue =
	 * "attachment; filename=transactionsByUser.xlsx"; response.setHeader(headerKey,
	 * headerValue);
	 * 
	 * List<Transaction> listTrans = transService.listTransactionByUserId(userId);
	 * 
	 * TransactionExcelExporter excelExporter = new
	 * TransactionExcelExporter(listTrans);
	 * 
	 * // excelExporter.export(response); return listTrans; }
	 * 
	 * // Get All Transaction For specific Year
	 * 
	 * @GetMapping("/allExcelY/{annee}") public String
	 * exportToExcelByYear(HttpServletResponse response, @PathVariable("annee") int
	 * annee) throws IOException {
	 * response.setContentType("application/octet-stream"); DateFormat dateFormatter
	 * = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss"); String currentDateTime =
	 * dateFormatter.format(new Date());
	 * 
	 * String headerKey = "Content-Disposition"; String headerValue =
	 * "attachment; filename=transactionsByYear.xlsx"; response.setHeader(headerKey,
	 * headerValue);
	 * 
	 * List<Transaction> listTrans = transService.listTransactionByDate(annee);
	 * 
	 * TransactionExcelExporter excelExporter = new
	 * TransactionExcelExporter(listTrans);
	 * 
	 * // String res=excelExporter.export(response);
	 * 
	 * return "successful generation of transactionsByYear.xlsx"; }
	 * 
	 * // Get statistic Months / amount BY year
	 * 
	 * @GetMapping("/statistic/{year}") public String
	 * statisticMonthByAmount(@PathVariable("year") int year) throws IOException {
	 * Map<Double, Double> MA = new HashMap<Double, Double>(); MA =
	 * transService.StatisticMonthbyAmount(year); String json = new
	 * ObjectMapper().writeValueAsString(MA); return json;
	 * 
	 * }
	 */

}
