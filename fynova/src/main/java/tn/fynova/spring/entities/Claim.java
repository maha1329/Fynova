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
	
	
}
