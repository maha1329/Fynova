package tn.fynova.spring.control;

import java.io.IOException;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.export.Exporter;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.hbar.HorizontalBarChartDataSet;
import org.primefaces.model.charts.hbar.HorizontalBarChartModel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import tn.fynova.spring.entities.Credit;
import tn.fynova.spring.entities.Operation;
import tn.fynova.spring.entities.Project;
import tn.fynova.spring.entities.Transaction;
import tn.fynova.spring.repository.CreditRepository;
import tn.fynova.spring.repository.ProjectRepository;
import tn.fynova.spring.repository.UserRepository;
import tn.fynova.spring.service.ICreditService;
import tn.fynova.spring.service.IProjectService;
import tn.fynova.spring.service.ITransactionService;
import tn.fynova.spring.service.TransactionExcelExporter;
import tn.fynova.spring.service.fileExceptions.TextExporter;

@Controller(value = "TransactionController") // Name of the bean in Spring IoC
@ELBeanName(value = "TransactionController") // Name
public class TransactionController {

	private int transactionid;
	private Date transactionDate;
    private Transaction t;
	private int idprojet;
    private Exporter<DataTable> textExporter=new TextExporter();

    
	public Exporter<DataTable> getTextExporter() {
		return textExporter;
	}

	public void setTextExporter(Exporter<DataTable> textExporter) {
		this.textExporter = textExporter;
	}

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
	IProjectService projectService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	TwilioController twilioCon;

	private int idCredit;
	private int nbreC;
	private float amount;
	
    private HorizontalBarChartModel hbarModel=new HorizontalBarChartModel();


	public HorizontalBarChartModel getHbarModel() {
		return hbarModel;
	}

	public void setHbarModel(HorizontalBarChartModel hbarModel) {
		this.hbarModel = hbarModel;
	}

	private PieChartModel pieModel;

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
	public void payWithCoupon(int idprojet) {
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

		// twilioCon.sendotp("+216" + c.getCredit_account().getAccount_user().getUser_phone());
		System.out.print("passe");
		FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Transaction in progress"));
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
	 */
	  //excelExporter.export(response); return listTrans; }
	  
	  // Get All Transaction For specific User
	  
	  /*@GetMapping("/allExcelU/{UserID}") public List<Transaction>
	  exportToExcelByUser(HttpServletResponse response, @PathVariable("UserID") int
	  userId) throws IOException {
	  response.setContentType("application/octet-stream"); DateFormat dateFormatter
	  = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss"); String currentDateTime =
	  dateFormatter.format(new Date());
	  
	  String headerKey = "Content-Disposition"; String headerValue =
	  "attachment; filename=transactionsByUser.xlsx"; response.setHeader(headerKey,
	  headerValue);
	  
	  List<Transaction> listTrans = transService.listTransactionByUserId(userId);
	  
	  TransactionExcelExporter excelExporter = new
	  TransactionExcelExporter(listTrans);
	  */
	  // excelExporter.export(response); return listTrans; }
	  
	  // Get All Transaction For specific Year
	  
	/*  @GetMapping("/allExcelY/{annee}") public String
	  exportToExcelByYear(HttpServletResponse response, @PathVariable("annee") int
	  annee) throws IOException {
	  response.setContentType("application/octet-stream"); DateFormat dateFormatter
	  = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss"); String currentDateTime =
	  dateFormatter.format(new Date());
	  
	  String headerKey = "Content-Disposition"; String headerValue =
	  "attachment; filename=transactionsByYear.xlsx"; response.setHeader(headerKey,
	  headerValue);
	  
	  List<Transaction> listTrans = transService.listTransactionByDate(annee);
	  
	  TransactionExcelExporter excelExporter = new
	  TransactionExcelExporter(listTrans);
	  
	  // String res=excelExporter.export(response);
	  
	  return "successful generation of transactionsByYear.xlsx"; }
	  */
	  // Get statistic Months / amount BY year
	  
	 public String statisticMonthByAmount(int year) throws IOException {
	  Map<Double, Double> MA = new HashMap<Double, Double>(); MA =
	  transService.StatisticMonthbyAmount(year); String json = new
	  ObjectMapper().writeValueAsString(MA); return json;	  
	  }
	 

	
	 public Transaction getT() {
		return t;
	}

	public void setT(Transaction t) {
		this.t = t;
	}

	public PieChartModel getPieModel() {
		return pieModel;
	}

	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}

	public void showMessage() {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", " Always Bet on Prime!");

	        PrimeFaces.current().dialog().showMessageDynamic(message);
	    }
	 
	 public PieChartModel createPieModel(float projectcost) {
	        pieModel = new PieChartModel();
	        ChartData data = new ChartData();
	        float amount=transService.SumTransAmount();
	        System.out.print(amount);
	        float val=(amount*100)/projectcost;
	        System.out.print(val);
	        PieChartDataSet dataSet = new PieChartDataSet();
	        List<Number> values = new ArrayList<>();
	        values.add(val);
	        values.add(100-val);
	        dataSet.setData(values);

	        List<String> bgColors = new ArrayList<>();
	        bgColors.add("rgb(107,142,35)");
	        bgColors.add("rgb(255,255,0)");
	        dataSet.setBackgroundColor(bgColors);

	        data.addChartDataSet(dataSet);
	        List<String> labels = new ArrayList<>();
	        labels.add("Collected in percentage");
	        labels.add("To collect in percentage");
	        data.setLabels(labels);

	        pieModel.setData(data);
	        return pieModel;
	    }
	 
	 public HorizontalBarChartModel createHorizontalBarModel() {
	        Date d=new Date();  
	        int year=d.getYear();  
	        int currentYear=year+1900;  
	        System.out.println("Current year is : "+currentYear);  
	    	
	        Map<Double, Double> MA = new HashMap<Double, Double>(); 
	    	MA =transService.StatisticMonthbyAmount(currentYear); 
		    ChartData data = new ChartData();

	        HorizontalBarChartDataSet hbarDataSet = new HorizontalBarChartDataSet();
	        List<Number> values = new ArrayList<>();
	        values.add(1);
	        values.add(1);
	        values.add(1);
	        values.add(1);
	        values.add(1);
	        values.add(1);
	        values.add(1);
	        values.add(1);
	        values.add(1);
	        values.add(1);
	        values.add(1);
	        values.add(1);
	      
	        for (Map.Entry mapentry : MA.entrySet()) {
	           /* System.out.println("clé: "+mapentry.getKey() 
	                               + " | valeur: " + mapentry.getValue());*/
	        	
	        	if((double)mapentry.getKey()==1) {
	        		values.remove(0);
	        		values.add(0, (Number)mapentry.getValue());
	        		
	        	}
	        	else if((double)mapentry.getKey()==2) {
	        		values.remove(1);
	        		values.add(1, (Number)mapentry.getValue());
	        	}
	        	else if((double)mapentry.getKey()==3) {
	        		values.remove(2);
	        		values.add(2, (Number)mapentry.getValue());
	        	}
	        	else if((double)mapentry.getKey()==4) {
	        		values.remove(3);
	        		values.add(3, (Number)mapentry.getValue());
	        		}
	        	else if((double)mapentry.getKey()==5) {
	        		values.remove(4);
	        		values.add(4, (Number)mapentry.getValue());
	        	//	values.add((Number) mapentry.getValue());
	        	}
	        	else if((double)mapentry.getKey()==6) {
	        		values.remove(5);
	        		values.add(5, (Number)mapentry.getValue());
	        	}
	        	else if((double)mapentry.getKey()==7) {
	        		values.remove(6);
	        		values.add(6, (Number)mapentry.getValue());
	        	}
	        	else if((double)mapentry.getKey()==8) {
	        		values.remove(7);
	        		values.add(7, (Number)mapentry.getValue());
	        	}
	        	else if((double)mapentry.getKey()==9) {
	        		values.remove(8);
	        		values.add(8, (Number)mapentry.getValue());
	        	}
	        	else if((double)mapentry.getKey()==10) {
	        		values.remove(9);
	        		values.add(9, (Number)mapentry.getValue());
	        	}
	        	else if((double)mapentry.getKey()==11) {
	        		values.remove(10);
	        		values.add(10, (Number)mapentry.getValue());
	        		}
	        	else if((double)mapentry.getKey()==12) {
	        		values.remove(11);
	        		values.add(11, (Number)mapentry.getValue());
	        	}
	        	
	         }
 
	        
	        //List<double> values = new ArrayList<>();
	       /* values.add(65);
	        values.add(59);
	        values.add(80);
	        values.add(81);
	        values.add(56);
	        values.add(55);
	        values.add(40);*/
	        hbarDataSet.setData(values);

	        List<String> bgColor = new ArrayList<>();
	        bgColor.add("rgba(255, 99, 132, 0.2)");
	        bgColor.add("rgba(255, 159, 64, 0.2)");
	        bgColor.add("rgba(255, 205, 86, 0.2)");
	        bgColor.add("rgba(75, 192, 192, 0.2)");
	        bgColor.add("rgba(54, 162, 235, 0.2)");
	        bgColor.add("rgba(153, 102, 255, 0.2)");
	        bgColor.add("rgba(201, 203, 207, 0.2)");
	        hbarDataSet.setBackgroundColor(bgColor);

	        List<String> borderColor = new ArrayList<>();
	        borderColor.add("rgb(255, 99, 132)");
	        borderColor.add("rgb(255, 159, 64)");
	        borderColor.add("rgb(255, 205, 86)");
	        borderColor.add("rgb(75, 192, 192)");
	        borderColor.add("rgb(54, 162, 235)");
	        borderColor.add("rgb(153, 102, 255)");
	        borderColor.add("rgb(201, 203, 207)");
	        hbarDataSet.setBorderColor(borderColor);
	        hbarDataSet.setBorderWidth(1);

	        data.addChartDataSet(hbarDataSet);

	        List<String> labels = new ArrayList<>();
	        labels.add("January");
	        labels.add("February");
	        labels.add("March");
	        labels.add("April");
	        labels.add("May");
	        labels.add("June");
	        labels.add("July");
	        labels.add("August");
	        labels.add("September");
	        labels.add("October");
	        labels.add("November");
	        labels.add("December");
	        data.setLabels(labels);
	        hbarModel.setData(data);
	        
	        //Options
	        BarChartOptions options = new BarChartOptions();
	        CartesianScales cScales = new CartesianScales();
	        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
	        linearAxes.setOffset(true);
	        CartesianLinearTicks ticks = new CartesianLinearTicks();
	        ticks.setBeginAtZero(true);
	        linearAxes.setTicks(ticks);
	        cScales.addXAxesData(linearAxes);
	        options.setScales(cScales);

	        Title title = new Title();
	        title.setDisplay(true);
	        title.setText("Statistic amount by month for current year "+ currentYear);
	        options.setTitle(title);

	        hbarModel.setOptions(options);
	        return hbarModel;
	    }
}
