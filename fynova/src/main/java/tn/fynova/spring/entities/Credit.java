package tn.fynova.spring.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class Credit implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int credit_id;
	private float credit_amout;
	@Temporal(TemporalType.DATE)
	private Date credit_grantDate;
	@Temporal(TemporalType.DATE)
	private Date credit_deadline;
	private float credit_interestRate;
	
	@ManyToOne
	private Account credit_account;
}
