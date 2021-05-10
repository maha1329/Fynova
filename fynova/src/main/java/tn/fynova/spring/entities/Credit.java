package tn.fynova.spring.entities;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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

	private int step;

	private int numContract;

	

	@OneToOne(mappedBy = "credit")
    private Account account;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "transactionCredit")
	private List<Transaction> transactionCredit;

	@OneToMany(mappedBy = "credit", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<LogCredit> pages; 
	
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

	

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getNumContract() {
		return numContract;
	}

	public void setNumContract(int numContract) {
		this.numContract = numContract;
	}

	
	public List<Transaction> getTransactionCredit() {
		return transactionCredit;
	}

	public void setTransactionCredit(List<Transaction> transactionCredit) {
		this.transactionCredit = transactionCredit;
	}

	public Set<LogCredit> getPages() {
		return pages;
	}

	public void setPages(Set<LogCredit> pages) {
		this.pages = pages;
	}
	
	

}
