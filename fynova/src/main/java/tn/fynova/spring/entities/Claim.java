package tn.fynova.spring.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Claim implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int claim_id;
	
	private String claim_title;
	private String  claim_description;
	private String status;
	private Date dateclaim;
	private Date datereponse;
	@ManyToOne
	@JsonIgnore  //bech mayjibch m3ah foreignkey
	private User claim_user;

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateclaim() {
		return dateclaim;
	}

	public void setDateclaim(Date dateclaim) {
		this.dateclaim = dateclaim;
	}



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
	

	public Claim() {
		super();
	}

	public Date getDatereponse() {
		return datereponse;
	}

	public void setDatereponse(Date datereponse) {
		this.datereponse = datereponse;
	}

	public Claim(int claim_id, String claim_title, String claim_description, String status, Date dateclaim,
			Date datereponse, User claim_user) {
		super();
		this.claim_id = claim_id;
		this.claim_title = claim_title;
		this.claim_description = claim_description;
		this.status = status;
		this.dateclaim = dateclaim;
		this.datereponse = datereponse;
		this.claim_user = claim_user;
	}

	@Override
	public String toString() {
		return "Claim [claim_id=" + claim_id + ", claim_title=" + claim_title + ", claim_description="
				+ claim_description + ", status=" + status + ", dateclaim=" + dateclaim + ", datereponse=" + datereponse
				+ ", claim_user=" + claim_user + "]";
	}
	

	
	
}
