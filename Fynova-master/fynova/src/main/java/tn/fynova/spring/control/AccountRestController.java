package tn.fynova.spring.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.fynova.spring.entities.Account;
import tn.fynova.spring.entities.Claim;
import tn.fynova.spring.service.IAccountService;
import tn.fynova.spring.service.ICreditService;

@RestController
@RequestMapping("/account")
public class AccountRestController {

	
	@Autowired
	IAccountService servaccount;

	@Autowired
	ICreditService creditService;
	
	
	@PostMapping("/addaccount/{userid}")
	@ResponseBody
	//@PreAuthorize("hasAuthority('employee') or hasAuthority('customer')")
	public Account Addaccount(@RequestBody Account e,@PathVariable("userid")int userid  )
	{
		return servaccount.addAccount(e,userid);
	
	}
	
    @PutMapping("/modify-Account")
    @ResponseBody
    public Account modifyAnnonce(@RequestBody Account rec) {
        return servaccount.updateAcount(rec);  }

	
	
	@DeleteMapping("/remove/{idaccount}")
	@ResponseBody
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


	@GetMapping("/checkAccount/{id}")
	public String AccountCompteUser(@PathVariable("id")int id )
	{
		return servaccount.checkAccount(id);
	}


	@GetMapping("/checkAccountDedlean/{id}")
	public String AccountCompteDeadlineUser(@PathVariable("id")int id )
	{
		return servaccount.checkAccountDedlean(id);
	}

	@GetMapping("/cheStatus/{id}")
	public void CheckStatus(@PathVariable("id")int id )
	{
		 servaccount.chechStatus(id);
	}

	@GetMapping("/cheStatusAllUser")
	public void CheckStatusAllUser()
	{
		servaccount.chechAccountAllUser();
	}

	@GetMapping("/StatistiqueNbrRisuqe")
	public String StatistiqueNbrRisuqe()
	{
		return servaccount.StatistiqueNbrRisuqe();
	}

	@GetMapping("/StatistiqueNbrRisuqeParTotal")
	public String StatistiqueNbrRisuqeParTotal()
	{
	 return servaccount.StatistiqueNbrRisuqeParTotal();
	}


}
