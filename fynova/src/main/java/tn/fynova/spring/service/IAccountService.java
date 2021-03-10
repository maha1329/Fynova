package tn.fynova.spring.service;

import java.util.List;
import java.util.Optional;

import tn.fynova.spring.entities.Account;

public interface IAccountService {
	Account addAccount(Account e);
	void deleteAccount(int id);
	List<Account> retrieveAllAccounts();
	Optional<Account>  retrieveAccount(int id);
}
