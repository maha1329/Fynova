package tn.fynova.spring.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Account implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int account_id;
	private float account_balance;
	@Temporal(TemporalType.DATE)
	private Date account_creationDate;
	@Enumerated
	private AccountType account_type;

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

	public User getAccount_user() {
		return account_user;
	}

	public void setAccount_user(User account_user) {
		this.account_user = account_user;
	}

	@ManyToOne
	private User account_user;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "credit_account")
	private List<Credit> account_credits;

	// @OneToMany(cascade = CascadeType.ALL, mappedBy="transaction_account")
	// private List<Transaction> account_transactions;

}
