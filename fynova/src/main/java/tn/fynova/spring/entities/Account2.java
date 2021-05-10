package tn.fynova.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
@Entity
public class Account2 implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int account_id;
	private float account_balance;
	@Temporal(TemporalType.DATE)
	private Date account_creationDate;
	@Enumerated(EnumType.STRING)
	private AccountType account_type;
	private String Status;
	private String etat;
	private int nbrBloque;
	
	
	
	//private int nombre_de_bloquages;
	@ManyToOne
	@JsonIgnore
	private User accountuser;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "credit_id", referencedColumnName = "credit_id")
	private Credit credit;
	
	
	public Credit getCredit() {
		return credit;
	}

	public void setCredit(Credit credit) {
		this.credit = credit;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
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

	

	public User getAccountuser() {
		return accountuser;
	}

	public void setAccountuser(User accountuser) {
		this.accountuser = accountuser;
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

//@OneToMany(cascade = CascadeType.ALL, mappedBy="transaction_account")
	//private List<Transaction> account_transactions;
	
}
