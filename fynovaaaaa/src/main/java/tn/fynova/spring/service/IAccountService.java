package tn.fynova.spring.service;

import java.util.List;
import java.util.Optional;

import tn.fynova.spring.entities.Account;

public interface IAccountService {
	Account addAccount(Account e, int userid);

	void deleteAccount(int id);

	List<Account> retrieveAllAccounts();

	Account retrieveAccount(int id);

	Account findAccountByIdUser(int id);

	Account updateAcount(Account r);

	String checkAccount(int idc);

	String checkAccountDedlean(int idu);

	void chechStatus(int idu);

	void chechAccountAllUser();

	String StatistiqueNbrRisuqe();

	String StatistiqueNbrRisuqeParTotal();

}
