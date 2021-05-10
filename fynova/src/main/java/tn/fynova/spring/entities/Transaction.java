package tn.fynova.spring.entities;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Transaction implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionid;

	@Temporal(TemporalType.DATE)
	private Date transactionDate;

	private float transactionAmount;

	@Enumerated
	private Operation transactionType;

	@ManyToOne
	private Credit transactionCredit;

	private int nbreC;
	private float amountC;

	private Currency currency;

	private Status status;

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getNbreC() {
		return nbreC;
	}

	public void setNbreC(int nbreC) {
		this.nbreC = nbreC;
	}

	public float getAmountC() {
		return amountC;
	}

	public void setAmountC(float amountC) {
		this.amountC = amountC;
	}

	public Credit getTransaction_credit() {
		return transactionCredit;
	}

	public void setTransaction_credit(Credit transaction_credit) {
		this.transactionCredit = transaction_credit;
	}

	public Transaction(Date transaction_date, float transaction_amount, Operation transaction_type) {
		super();
		this.transactionDate = transaction_date;
		this.transactionAmount = transaction_amount;
		this.transactionType = transaction_type;
	}

	public Transaction() {
		super();
	}

	public int getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(int transactionid) {
		this.transactionid = transactionid;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public float getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(float transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Operation getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(Operation transactionType) {
		this.transactionType = transactionType;
	}

	public Credit getTransactionCredit() {
		return transactionCredit;
	}

	public void setTransactionCredit(Credit transactionCredit) {
		this.transactionCredit = transactionCredit;
	}

	@Override
	public String toString() {
		return "Transaction [transactionid=" + transactionid + ", transactionDate=" + transactionDate
				+ ", transactionAmount=" + transactionAmount + ", transactionType=" + transactionType
				+ ", transactionCredit=" + transactionCredit + ", nbreC=" + nbreC + ", amountC=" + amountC
				+ ", currency=" + currency + ", status=" + status + "]";
	}

	
}
