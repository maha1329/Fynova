package tn.fynova.spring.service;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.fynova.spring.entities.*;
import tn.fynova.spring.repository.*;


@Service
@Transactional
public class AccountServiceImpl implements IAccountService {
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	HistoriqueRepository historyRepo ;

	@Autowired
	IUserService userService ;

	@Autowired
	CreditRepository creditService;
	@Autowired
	CreditRepository creditRepository;
	@Override
	public Account addAccount(Account e,int userid) {
		//Credit credit =creditRepository.findById(account_credits).orElse(null);
		User account_user = userRepository.findById(userid).orElse(null);
		e.setAccount_user(account_user);
		e.setEtat("Non Bloque");
		e.setAccount_creationDate(new Date());
		//e.getAccount_credits().add(credit);
	  	//e.setAccount_type("current");
		e.setStatus("Stable");
		accountRepository.save(e);
		return e;
	}

	@Override
	public void deleteAccount(int id) {
		accountRepository.deleteById(id);		
	}

	@Override
	public List<Account> retrieveAllAccounts() {
		List<Account> accounts = (List<Account>)accountRepository.findAll();
		return accounts;
	}

	@Override
	public Account retrieveAccount(int id) {
		Account account = accountRepository.findById(id).orElse(null);
		return account;
	}

	@Override
	public Account updateAcount(Account r) {
		
		Account accountToupdate = accountRepository.findById(r.getAccount_id()).orElse(null);
		accountToupdate.setAccount_balance(r.getAccount_balance());
		//accountToupdate.setAccount_creationDate(r.getAccount_creationDate());
		accountToupdate.setAccount_type(r.getAccount_type());
		//accountToupdate.setEtat(r.getEtat());
		//accountToupdate.setStatus(r.getStatus());
		//accountToupdate.setNbrBloque(r.getNbrBloque());
		
		return this.accountRepository.save(accountToupdate);
	}

	@Override
	public Account findAccountByIdUser(int id) {
		Account account =  accountRepository.AccountByUser(userRepository.findById(id).orElse(null));
		return account;
	}



	@Override
	public String checkAccount(int idu) {

		Account a = retrieveAccount(findAccountByIdUser(idu).getAccount_id());
		Credit c =creditService.CreditByAccount(a);
		String affiche ;
		LocalDate d = LocalDate.now();
		Date date = java.sql.Date.valueOf(d);

		System.out.println(c.getCredit_deadline());


		Date dd=  new java.sql.Date( c.getCredit_deadline().getTime());

		long diff = dd.getTime() - date.getTime();
		float res = (diff / (1000*60*60*24));

		if ((res == 0) && (a.getAccount_balance()<c.getCredit_amount())){
			a.setEtat("En Cours");
			updateAcount(a);
			affiche = "nous allons vous étendre la période " ;

		}
		else{
			affiche=    "non bloque";//a.setEtat (non bloque

		}

		return affiche;





	}



	@Override
	public String checkAccountDedlean(int idu) {

		Account a = retrieveAccount(findAccountByIdUser(idu).getAccount_id());
		Credit c =creditService.CreditByAccount(a);
		String affiche ="ffff";
		LocalDate d = LocalDate.now();
		Date date = java.sql.Date.valueOf(d);

		LocalDate aaa = new java.sql.Date(c.getCredit_deadline().getTime()).toLocalDate();
		LocalDate fff = aaa.plusDays(3);
		Date dd = java.sql.Date.valueOf(fff);


		long diff = dd.getTime() - date.getTime();
		float res = (diff / (1000*60*60*24));

		if  ((res == 0) && (a.getAccount_balance()<c.getCredit_amount())) {

				a.setNbrBloque(a.getNbrBloque() + 1);
				a.setEtat("bloque");

				updateAcount(a);
				 	affiche = "bloque" ;
			} else {
				a.setEtat("non bloque");

				updateAcount(a);
				  affiche = "non bloque" ;//zeyed

			}



		return affiche;

	}

	@Override
	public void chechStatus(int idu) {
		Account a = retrieveAccount(findAccountByIdUser(idu).getAccount_id());
		Historique h = new Historique();
		if(a.getNbrBloque()>=4){
			h.setStatus("a risque");
			h.setAccount(a);
			h.setUser(a.getAccount_user());
			h.setDateh(new Date());
			historyRepo.save(h);
			a.setStatus("a risque");

		}
		else if(2 <a.getNbrBloque() && a.getNbrBloque()<4) {
			h.setStatus("Favorable");
			h.setAccount(a);
			h.setUser(a.getAccount_user());
			h.setDateh(new Date());
			historyRepo.save(h);
			a.setStatus("Favorable");
		}
       else{

			h.setStatus("Stable");
			h.setAccount(a);
			h.setUser(a.getAccount_user());
			h.setDateh(new Date());
			historyRepo.save(h);

	a.setStatus("Stable");
		}
	}

	@Override
	public void chechAccountAllUser() {
		List<User> u = userService.retrieveAllUsers();

		for (User e :u){
			checkAccount(e.getUser_id());
			checkAccountDedlean(e.getUser_id());
			chechStatus(e.getUser_id());

		}
	}
	@Override
	public String StatistiqueNbrRisuqe(){
      //float m = (accountRepository.NbrStatus("a risque")/accountRepository.NbrStatus("Stable"))*100;
         float  m= accountRepository.NbrStatus("a risque");
        float  s=accountRepository.NbrStatus("Stable");
       float res =( m / s)*100;
      return res+"%";

	}

	@Override
	public String StatistiqueNbrRisuqeParTotal() {
		float a = userRepository.NbrCustmer(Role.Customer);
		float m = ( accountRepository.NbrStatus("a risque")/a)*100;

		return m+"%";
	}

	
}
