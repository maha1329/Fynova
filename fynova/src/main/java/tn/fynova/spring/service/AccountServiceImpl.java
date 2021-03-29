package tn.fynova.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.fynova.spring.entities.Account;
import tn.fynova.spring.repository.AccountRepository;

@Service
@Transactional

public class AccountServiceImpl implements IAccountService {
	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public Account addAccount(Account e) {
		Account account = accountRepository.save(e);
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
	public Optional<Account> retrieveAccount(int id) {
		Optional<Account>  account = accountRepository.findById(id);
		return account;
	}
	
	
}
