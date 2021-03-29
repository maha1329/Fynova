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
public class Association implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int association_id;
	
	private int association_fiscalNumber;
	
	private String association_name;
	private String association_description;
	
	@Temporal(TemporalType.DATE)
	private Date association_fondationDate;
	private int association_customersNumber;

}
