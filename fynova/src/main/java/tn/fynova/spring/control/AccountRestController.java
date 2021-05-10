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
import tn.fynova.spring.entities.Account2;
import tn.fynova.spring.entities.Claim;
import tn.fynova.spring.entities.Project;
import tn.fynova.spring.entities.Transaction;
import tn.fynova.spring.entities.User;
import tn.fynova.spring.repository.AccountRepository;
import tn.fynova.spring.repository.UserRepository;
import tn.fynova.spring.service.IAccountService;
import tn.fynova.spring.service.ICreditService;

@RestController
@RequestMapping("/account")
public class AccountRestController {

	@Autowired
	IAccountService servaccount;

	@Autowired
	ICreditService creditService;
	@Autowired
	AccountRepository repoac;

	@Autowired
	UserRepository userrepo;

	@GetMapping("/all/{id}")
	@ResponseBody
	public List<Account> getAc(@PathVariable("id") int id) {
		User u=userrepo.findById(id).get();
		System.out.print(u.toString());
		List<Account> list = (List<Account>) repoac.findAllByaccountuser(u);
		return list;
	}

	/*
	 * @PostMapping("/addaccount/{userid}/{creditid}/{balance}/{nbre}")
	 * 
	 * @ResponseBody
	 * // @PreAuthorize("hasAuthority('employee') or hasAuthority('customer')")
	 * public Account2 Addaccount(@PathVariable("userid") int
	 * userid, @PathVariable("creditid") int creditid,
	 * 
	 * @PathVariable("balance") float balance, @PathVariable("nbre") int nbre) {
	 * Account2 e = new Account2(); e.setAccount_balance(balance);
	 * e.setNbrBloque(nbre); return servaccount.addAccount(e, userid, creditid); }
	 */

	@PostMapping("/addaccount/{userid}/{creditid}")

	@ResponseBody
	// @PreAuthorize("hasAuthority('employee') or hasAuthority('customer')")
	public Account Addaccount(@RequestBody Account e, @PathVariable("userid") int userid,
			@PathVariable("userid") int creditid) {
		return servaccount.addAccount(e, userid, creditid);

	}

	@PutMapping("/modify-Account")
	@ResponseBody
	public Account modifyAnnonce(@RequestBody Account rec) {
		return servaccount.updateAcount(rec);
	}

	@DeleteMapping("/remove/{idaccount}")
	@ResponseBody
	// @PreAuthorize("hasAuthority('employee') or hasAuthority('customer')")
	public void DeleteAccount(@PathVariable("idaccount") int id) {
		servaccount.deleteAccount(id);
	}

	@GetMapping("/retrieveaccount/{idaccount}")
	public Account RetrieveAccount(@PathVariable("idaccount") int id) {
		return servaccount.retrieveAccount(id);
	}

	@GetMapping("/checkAccount/{id}")
	public String AccountCompteUser(@PathVariable("id") int id) {
		return servaccount.checkAccount(id);
	}

	@GetMapping("/checkAccountDedlean/{id}")
	public String AccountCompteDeadlineUser(@PathVariable("id") int id) {
		return servaccount.checkAccountDedlean(id);
	}

	@GetMapping("/cheStatus/{id}")
	public void CheckStatus(@PathVariable("id") int id) {
		servaccount.chechStatus(id);
	}

	@GetMapping("/cheStatusAllUser")
	public void CheckStatusAllUser() {
		servaccount.chechAccountAllUser();
	}

	@GetMapping("/StatistiqueNbrRisuqe")
	public String StatistiqueNbrRisuqe() {
		return servaccount.StatistiqueNbrRisuqe();
	}

	@GetMapping("/StatistiqueNbrRisuqeParTotal")
	public String StatistiqueNbrRisuqeParTotal() {
		return servaccount.StatistiqueNbrRisuqeParTotal();
	}

	/*
	 * @GetMapping("/user/{id}")
	 * 
	 * @ResponseBody public List<Account> get(@PathVariable("id") String id) { User
	 * u = userrepo.findById(id).get(); List<Account> t = repoac.findAllByEtat(id);
	 * return t;
	 * 
	 * }
	 */

}
