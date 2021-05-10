package tn.fynova.spring.service;

import java.util.List;
import java.util.Optional;

import tn.fynova.spring.entities.Account;
import tn.fynova.spring.entities.Account2;

public interface IAccountService {
	
	Account addAccount(Account e, int userid,int creditid);
	void deleteAccount(int id);

	List<Account> retrieveAllAccounts();

	Account retrieveAccount(int id);

	List<Account> findAccountByIdUser(int id);

	Account updateAcount(Account r);

	String checkAccount(int idc);

	String checkAccountDedlean(int idu);

	void chechStatus(int idu);

	void chechAccountAllUser();

	String StatistiqueNbrRisuqe();

	String StatistiqueNbrRisuqeParTotal();
	List<Account> findAllByEtat(String string);

}
