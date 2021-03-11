package tn.fynova.spring.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class Credit implements Serializable {
	@Id
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int credit_id;
	
	private float credit_amount;
	
	@Temporal(TemporalType.DATE)
	private Date credit_grantDate;
	
	@Temporal(TemporalType.DATE)
	private Date credit_deadline;
	
	private float credit_interestRate;
	
	@ManyToOne
	private Account credit_account;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="transaction_credit")
	private List<Transaction> transaction_credit;
	
	public int getCredit_id() {
		return credit_id;
	}

	public void setCredit_id(int credit_id) {
		this.credit_id = credit_id;
	}

	public float getCredit_amount() {
		return credit_amount;
	}

	public void setCredit_amount(float credit_amount) {
		this.credit_amount = credit_amount;
	}

	public Date getCredit_grantDate() {
		return credit_grantDate;
	}

	public void setCredit_grantDate(Date credit_grantDate) {
		this.credit_grantDate = credit_grantDate;
	}

	public Date getCredit_deadline() {
		return credit_deadline;
	}

	public void setCredit_deadline(Date credit_deadline) {
		this.credit_deadline = credit_deadline;
	}

	public float getCredit_interestRate() {
		return credit_interestRate;
	}

	public void setCredit_interestRate(float credit_interestRate) {
		this.credit_interestRate = credit_interestRate;
	}

	public Account getCredit_account() {
		return credit_account;
	}

	public void setCredit_account(Account credit_account) {
		this.credit_account = credit_account;
	}
	
	
}
