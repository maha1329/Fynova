package tn.fynova.spring.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.fynova.spring.entities.Account;
import tn.fynova.spring.entities.Claim;
import tn.fynova.spring.service.IAccountService;

@RestController
@RequestMapping("/account")
public class AccountRestController {

	
	@Autowired
	IAccountService servaccount;
	
	
	@PostMapping("/addaccount/{userid}")
	//@PreAuthorize("hasAuthority('employee') or hasAuthority('customer')")
	public Account Addaccount(@RequestBody Account e,@PathVariable("userid")int userid  )
	{
		return servaccount.addAccount(e,userid);
	
	}
	
	@DeleteMapping("/remove/{idaccount}")
	//@PreAuthorize("hasAuthority('employee') or hasAuthority('customer')")
	public void DeleteAccount(@PathVariable("idaccount") int id) 
	{
		servaccount.deleteAccount(id);
	}
	@GetMapping("/retrieveaccount/{idaccount}")
			public Account RetrieveAccount(@PathVariable("idaccount")int id )
			{
		return servaccount.retrieveAccount(id);
			}
}
