package tn.fynova.spring.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Demand {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int demandid;

	private String why;

	private Status status;

	private float amount;

	private int NbreC;
	 
	private Date DateDemand;

	private int userid;

	


	public int getUserid() {
		return userid;
	}


	public void setUserid(int userid) {
		this.userid = userid;
	}


	public int getDemandid() {
		return demandid;
	}


	public void setDemandid(int demandid) {
		this.demandid = demandid;
	}


	public Date getDateDemand() {
		return DateDemand;
	}


	public void setDateDemand(Date dateDemand) {
		DateDemand = dateDemand;
	}


	public Demand() {
		super();
	}


	public Demand( String why, Status status, float amount, int nbreC, Date dateDemand) {
		super();
		this.why = why;
		this.status = status;
		this.amount = amount;
		NbreC = nbreC;
		DateDemand = dateDemand;
	}


	public String getWhy() {
		return why;
	}

	public void setWhy(String why) {
		this.why = why;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getNbreC() {
		return NbreC;
	}

	public void setNbreC(int nbreC) {
		NbreC = nbreC;
	}

}
