package tn.fynova.spring.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.fynova.spring.entities.Account;
import tn.fynova.spring.entities.Claim;
import tn.fynova.spring.entities.User;
import tn.fynova.spring.repository.AccountRepository;
import tn.fynova.spring.repository.UserRepository;

@Service
@Transactional

public class AccountServiceImpl implements IAccountService {
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	UserRepository userRepository;
	
	@Override
	public Account addAccount(Account e,int userid) {
		
		Account account = new Account();
		User account_user = userRepository.findById(userid).orElse(null);
		account.setAccount_user(account_user);
		account.setAccount_balance("");
		account.setAccount_creationDate(new Date());
	//	account.setAccount_type("current");
		
		accountRepository.save(e);
		return account;
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


	
	
}
