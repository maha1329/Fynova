package tn.fynova.spring.entities;

import java.io.Serializable;
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
	private String account_balance;
	@Temporal(TemporalType.DATE)
	private Date account_creationDate;
	@Enumerated
	private AccountType account_type;
	
	@ManyToOne
	private User account_user;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="credit_account")
	private List<Credit> account_credits;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="transaction_account")
	private List<Transaction> account_transactions;
	
}
