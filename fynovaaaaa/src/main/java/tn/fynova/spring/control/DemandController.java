package tn.fynova.spring.control;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import tn.fynova.spring.service.IDemandService;
import tn.fynova.spring.service.ITransactionService;
import tn.fynova.spring.service.IUserService;


@RestController
@RequestMapping("/demandRetrait")
public class DemandController {

	
	@Autowired
	IDemandService demandService;
	
	@Autowired
	ITransactionService transService;
	
	@Autowired
	IUserService userService;
	
	@GetMapping("/all/{id}")
	@ResponseBody
	public Demand getDemand(@PathVariable("id") int id) {
		Demand t = demandService.retrievedemand(id);
		return t;

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
	

	@PostMapping("/DemandAccept/{demandid}")
	@ResponseBody
	public Boolean AcceptRetrait(@PathVariable("demandid") int demandid) {
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
		return true;
	}

	@PostMapping("/DemandRefus/{demandid}")
	@ResponseBody
	public Boolean RefusRetrait(@PathVariable("demandid") int demandid) {
		Date date = convertToDateViaSqlTimestamp(LocalDateTime.now());
		Demand d=demandService.retrievedemand(demandid);
		d.setStatus(Status.failure);
		demandService.adddemand(d);
		return true;
	}

}
