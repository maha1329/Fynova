package tn.fynova.spring.control;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.fynova.spring.entities.Credit;
import tn.fynova.spring.entities.Operation;
import tn.fynova.spring.entities.Transaction;
import tn.fynova.spring.repository.CreditRepository;
import tn.fynova.spring.service.CreditServiceImpl;
import tn.fynova.spring.service.TransactionServiceImpl;

@RestController
@RequestMapping("/transaction")
public class TransactionRestController {

	@Autowired
	TransactionServiceImpl transService;
	
	@Autowired
	CreditServiceImpl creditService;
	
	@Autowired
	CreditRepository creditRepository;
	
	@GetMapping("/all")
	@ResponseBody
	public List<Transaction> getTransaction (){

	List<Transaction> list =  transService.retrieveAllTransactions();
	return list;
	}
	
	@GetMapping("/all/{id}")
	@ResponseBody
	public Transaction getTransaction (@PathVariable("id")int id){
	  Transaction t =transService.retrieveTransactions(id);
	  return t;
	
	}
	
	//Conversion
	public Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
	    return java.sql.Timestamp.valueOf(dateToConvert);
	}
	
	
	//as an investor
	@PostMapping("/GiveMoney/{idCredit}/{nbreC}/{amount}")
	@ResponseBody
	public Boolean payWithCoupon (@PathVariable("idCredit")int idCredit,@PathVariable("nbreC")int nbreC,@PathVariable("amount")float amount){
		Date d=convertToDateViaSqlTimestamp(LocalDateTime.now());
		float sum=amount * nbreC;
		Optional<Credit> CreditOptional=creditRepository.findById(idCredit);
	    Credit c=CreditOptional.get();	        
		Transaction t =new Transaction(d,sum,Operation.debit);
        t.setTransaction_credit(c);
        t.setNbreC(nbreC);
        t. setAmountC(amount);
		transService.addTransaction(t);
		return true;
	}
	
	
}
