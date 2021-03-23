package tn.fynova.spring.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Claim implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int claim_id;
	private String claim_title;
	private String  claim_description;
	
	@ManyToOne
	private User claim_user;

	public int getClaim_id() {
		return claim_id;
	}

	public void setClaim_id(int claim_id) {
		this.claim_id = claim_id;
	}

	public String getClaim_title() {
		return claim_title;
	}

	public void setClaim_title(String claim_title) {
		this.claim_title = claim_title;
	}

	public String getClaim_description() {
		return claim_description;
	}

	public void setClaim_description(String claim_description) {
		this.claim_description = claim_description;
	}

	public User getClaim_user() {
		return claim_user;
	}

	public void setClaim_user(User claim_user) {
		this.claim_user = claim_user;
	}
	
	
}
