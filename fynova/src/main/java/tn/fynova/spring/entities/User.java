package tn.fynova.spring.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class User implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;
	private String user_password;
	private String user_email;
	private int user_phone;
	
	private String user_firstname;
	private String user_lastname;
	private int user_cin;
	
	@Temporal(TemporalType.DATE)
	private Date user_birthday;
	
	@Enumerated(EnumType.STRING)
	private Role user_role;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="claim_user")
	private List<Claim> user_claims;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="account_user")
	private List<Account> user_accounts;
	
}
