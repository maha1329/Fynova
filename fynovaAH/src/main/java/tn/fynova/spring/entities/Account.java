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
	@Enumerated(EnumType.STRING)
	private AccountType account_type;
	private String Status;
	private String etat;
	private int nbrBloque;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="credit_account")
	private List<Credit> account_credits;
	
	//private int nombre_de_bloquages;
	@ManyToOne
	@JsonIgnore
	private User account_user ;
	
	public List<Credit> getAccount_credits() {
		return account_credits;
	}

	public void setAccount_credits(List<Credit> account_credits) {
		this.account_credits = account_credits;
	}



	
	
	
	public Account(int account_id, float account_balance, AccountType account_type) {
		super();
		this.account_id = account_id;
		this.account_balance = account_balance;
		this.account_type = account_type;
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

	public User getAccount_user() {
		return account_user;
	}

	public void setAccount_user(User account_user) {
		this.account_user = account_user;
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

	public Account(int account_id, float account_balance, Date account_creationDate, AccountType account_type,
			String status, String etat, int nbrBloque, User account_user, List<Credit> account_credits) {
		super();
		this.account_id = account_id;
		this.account_balance = account_balance;
		this.account_creationDate = account_creationDate;
		this.account_type = account_type;
		Status = status;
		this.etat = etat;
		this.nbrBloque = nbrBloque;
		this.account_user = account_user;
		this.account_credits = account_credits;
	}

	public Account() {
		super();
	}

	@Override
	public String toString() {
		return "Account [account_id=" + account_id + ", account_balance=" + account_balance + ", account_creationDate="
				+ account_creationDate + ", account_type=" + account_type + ", Status=" + Status + ", etat=" + etat
				+ ", nbrBloque=" + nbrBloque + ", account_user=" + account_user + ", account_credits=" + account_credits
				+ "]";
	}

	public Account(float account_balance, Date account_creationDate, AccountType account_type, String status,
			String etat, int nbrBloque, User account_user, List<Credit> account_credits) {
		super();
		this.account_balance = account_balance;
		this.account_creationDate = account_creationDate;
		this.account_type = account_type;
		Status = status;
		this.etat = etat;
		this.nbrBloque = nbrBloque;
		this.account_user = account_user;
		this.account_credits = account_credits;
	}

	public Account(float account_balance, AccountType account_type) {
		super();
		this.account_balance = account_balance;
		this.account_type = account_type;
	}

	public Account(float account_balance, Date account_creationDate, AccountType account_type, String status,
			String etat, int nbrBloque) {
		super();
		this.account_balance = account_balance;
		this.account_creationDate = account_creationDate;
		this.account_type = account_type;
		Status = status;
		this.etat = etat;
		this.nbrBloque = nbrBloque;
	}

	
}
