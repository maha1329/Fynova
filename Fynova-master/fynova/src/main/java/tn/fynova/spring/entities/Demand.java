package tn.fynova.spring.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Demand implements Serializable {
     
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int demand_id;
	
	@Temporal(TemporalType.DATE)
	private Date demandDate;
	
	private String wording;
	
	private String source;
	
	private ETAT etat;
}
