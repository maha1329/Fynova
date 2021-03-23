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
	private int transaction_id;
	
	@Temporal(TemporalType.DATE)
	private Date transaction_date;

	private float transaction_amount;
	
	@Enumerated
	private Operation transaction_type;
	
    @ManyToOne
	private Credit transaction_credit;

    private int nbreC;
    private float amountC;
    
    
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
		return transaction_credit;
	}

	public void setTransaction_credit(Credit transaction_credit) {
		this.transaction_credit = transaction_credit;
	}

	public Transaction(Date transaction_date, float transaction_amount, Operation transaction_type) {
		super();
		this.transaction_date = transaction_date;
		this.transaction_amount = transaction_amount;
		this.transaction_type = transaction_type;
	}
	

	public Transaction() {
		super();
	}

	public int getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}

	public Date getTransaction_date() {
		return transaction_date;
	}

	public void setTransaction_date(Date transaction_date) {
		this.transaction_date = transaction_date;
	}

	public float getTransaction_amount() {
		return transaction_amount;
	}

	public void setTransaction_amount(float transaction_amount) {
		this.transaction_amount = transaction_amount;
	}

	public Operation getTransaction_type() {
		return transaction_type;
	}

	public void setTransaction_type(Operation transaction_type) {
		this.transaction_type = transaction_type;
	}

	
	
	
}
