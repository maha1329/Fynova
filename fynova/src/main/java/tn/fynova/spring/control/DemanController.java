package tn.fynova.spring.control;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.fynova.spring.entities.Demand;
import tn.fynova.spring.entities.Operation;
import tn.fynova.spring.entities.Status;
import tn.fynova.spring.entities.Transaction;
import tn.fynova.spring.entities.User;
import tn.fynova.spring.repository.CreditRepository;
import tn.fynova.spring.repository.DemandRepository;
import tn.fynova.spring.repository.UserRepository;
import tn.fynova.spring.service.ICreditService;
import tn.fynova.spring.service.IDemandService;
import tn.fynova.spring.service.IProjectService;
import tn.fynova.spring.service.ITransactionService;
import tn.fynova.spring.service.IUserService;


@Controller(value = "DmdController") // Name of the bean in Spring IoC
@ELBeanName(value = "DmdController") // Name
public class DemanController {
	private int demandid;

	private String why;

	private Status status;

	private float amount;

	private int NbreC;
	 
	private Date DateDemand;

	private int userid;
	
	@Autowired
	IDemandService demandService;
	
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
	DemandRepository dmdRepository;
	
	private List<Demand> dmd;
	
	private List<Demand> dmdInProgress;
	
	public List<Demand> getDmdInProgress() {
		dmdInProgress = dmdRepository.findAllByStatus(Status.inprogress);
				return dmdInProgress;
	}

	public void setDmdInProgress(List<Demand> dmdInProgress) {
		this.dmdInProgress = dmdInProgress;
	}

	@Autowired
	IUserService userService;
	
	private Demand demand;
	
	private String username;
	
	
	public String getUsername(int userid) {
		return userRepository.findById(userid).get().getFirstname();
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Demand getDemand() {
		return demand;
	}

	public void setDemand(Demand demand) {
		this.demand = demand;
	}

	public int getDemandid() {
		return demandid;
	}

	public void setDemandid(int demandid) {
		this.demandid = demandid;
	}

	public String getWhy() {
		return why;
	}

	public void setWhy(String why) {
		this.why = why;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getNbreC() {
		return NbreC;
	}

	public void setNbreC(int nbreC) {
		NbreC = nbreC;
	}

	public Date getDateDemand() {
		return DateDemand;
	}

	public void setDateDemand(Date dateDemand) {
		DateDemand = dateDemand;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public IDemandService getDemandService() {
		return demandService;
	}

	public void setDemandService(IDemandService demandService) {
		this.demandService = demandService;
	}

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

	public IProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(IProjectService projectService) {
		this.projectService = projectService;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<Demand> getDmd() {
		dmd = dmdRepository.findAllByStatus(Status.success);

		return dmd;
	}

	public void setDmd(List<Demand> dmd) {
		this.dmd = dmd;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	
	

	// Conversion
	public Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
		return java.sql.Timestamp.valueOf(dateToConvert);
	}

	// as an investor , want my money
	@PostMapping("/Demand/{why}/{nbreC}/{amount}/{userid}")
	@ResponseBody
	public Boolean demandRetrait(@PathVariable("why") String why, @PathVariable("nbreC") int nbreC,
			@PathVariable("amount") float amount,@PathVariable("userid") int userid) {
		Date date = convertToDateViaSqlTimestamp(LocalDateTime.now());
        
		Demand d=new Demand();
		d.setDateDemand(date);
		d.setWhy(why);
		d.setNbreC(nbreC);
		d.setStatus(Status.inprogress);
		d.setAmount(amount);
		d.setUserid(userid);
		demandService.adddemand(d);
		return true;
	}
	

	
	public String AcceptRetrait(int demandid) {
		String navigateTo = "null";
		Date date = convertToDateViaSqlTimestamp(LocalDateTime.now());
		Demand d=demandService.retrievedemand(demandid);
		d.setStatus(Status.success);
		demandService.adddemand(d);
		Transaction t=new Transaction();
		t.setAmountC(d.getAmount());
		t.setNbreC(d.getNbreC());
		t.setTransactionDate(date);
		t.setTransactionType(Operation.credit);
		transService.addTransaction(t);
		System.out.print(true);
		navigateTo = "/pages/admin/listDmd.xhtml?faces-redirect=true\"";
		return navigateTo;
	}

	
	public String RefusRetrait(int demandid) {
		String navigateTo = "null";
		Date date = convertToDateViaSqlTimestamp(LocalDateTime.now());
		Demand d=demandService.retrievedemand(demandid);
		d.setStatus(Status.failure);
		demandService.adddemand(d);
		System.out.print(true);
		navigateTo = "/pages/admin/listDmd.xhtml?faces-redirect=true\"";
		return navigateTo;
	}

}
