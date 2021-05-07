package tn.fynova.spring.control;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.fynova.spring.entities.Account;
import tn.fynova.spring.entities.AccountType;
import tn.fynova.spring.entities.Credit;
import tn.fynova.spring.entities.User;
import tn.fynova.spring.service.IAccountService;

@Controller(value = "accountController") // Name of the bean in Spring IoC
@ELBeanName(value = "accountController") // Name of the bean used by JSF
public class AccountsController {
	
	
	private int account_id;
	private float account_balance;
	private Date account_creationDate;
	private AccountType account_type;
	private String Status;
	private String etat;
	private int nbrBloque;
	private int userid=1;
	private User account_user;
	private int accountIdToUpdate;

	private Credit account_credits;
	private List<Account> accounts;
	@Autowired
	IAccountService accountserv;
	
	
	public int getAccountIdToUpdate() {
		return accountIdToUpdate;
	}
	public void setAccountIdToUpdate(int accountIdToUpdate) {
		this.accountIdToUpdate = accountIdToUpdate;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public User getAccount_user() {
		return account_user;
	}
	public void setAccount_user(User account_user) {
		this.account_user = account_user;
	}
	public Credit getAccount_credits() {
		return account_credits;
	}
	public void setAccount_credits(Credit account_credits) {
		this.account_credits = account_credits;
	}
	public IAccountService getAccountserv() {
		return accountserv;
	}
	public void setAccountserv(IAccountService accountserv) {
		this.accountserv = accountserv;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	public float getAccount_balance() {
		return account_balance;
	}
	public void setAccount_balance(float account_balance) {
		this.account_balance = account_balance;
	}
	public Date getAccount_creationDate() {
		return account_creationDate;
	}
	public void setAccount_creationDate(Date account_creationDate) {
		this.account_creationDate = account_creationDate;
	}
	public AccountType getAccount_type() {
		return account_type;
	}
	public void setAccount_type(AccountType account_type) {
		this.account_type = account_type;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public int getNbrBloque() {
		return nbrBloque;
	}
	public void setNbrBloque(int nbrBloque) {
		this.nbrBloque = nbrBloque;
	}
	
	

public	void addAccount()
{	  
	Account e =new Account(account_balance,account_type);
	 accountserv.addAccount(e,userid);
	ResetAccountForm();

}

public	void deleteAccount(String account_id)
{
	accountserv.deleteAccount(Integer.valueOf(account_id) );
}

public	List<Account> getAccounts()
{
	accounts=accountserv.retrieveAllAccounts();
	return accounts;

}

public Account retrieveAccount(String account_id)
{
	return accountserv.retrieveAccount(Integer.valueOf(account_id));

}

public	Account findAccountByIdUser(String userid)
{
	return accountserv.findAccountByIdUser(Integer.valueOf(userid));
}

public	void updateAcount()
{
	accountserv.updateAcount(new Account(accountIdToUpdate,account_balance,account_type));
	ResetAccountForm();
}


public	void checkAccount(String  idc)
{
String check=accountserv.checkAccount(Integer.valueOf(idc));

	 addMessage(FacesMessage.SEVERITY_INFO,check, "Etat");
	
}

	
	
   

public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
    FacesContext.getCurrentInstance().
            addMessage(null, new FacesMessage(severity, summary, detail));
}

public	void checkAccountDedlean(String idu)
{
	String check=accountserv.checkAccountDedlean(Integer.valueOf(idu));

	 addMessage(FacesMessage.SEVERITY_INFO,check, "Etat");

}

public	void chechStatus(String idu)
{
	accountserv.chechStatus(Integer.valueOf(idu));
}

public	void chechAccountAllUser()
{
	accountserv.chechAccountAllUser();
}


public void displayAccount(Account 	account) {
    this.setAccount_balance(account.getAccount_balance());
    this.setAccount_type(account.getAccount_type());
    this.setAccountIdToUpdate(account.getAccount_id());
    this.setNbrBloque(account.getNbrBloque());
    this.setStatus(account.getStatus());
 
}

public void ResetAccountForm() {
    this.setAccount_balance(0);

    this.setAccount_type(null);

}

	
}
