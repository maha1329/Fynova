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
	public int getAssociation_id() {
		return association_id;
	}
	public void setAssociation_id(int association_id) {
		this.association_id = association_id;
	}
	public int getAssociation_fiscalNumber() {
		return association_fiscalNumber;
	}
	public void setAssociation_fiscalNumber(int association_fiscalNumber) {
		this.association_fiscalNumber = association_fiscalNumber;
	}
	public String getAssociation_name() {
		return association_name;
	}
	public void setAssociation_name(String association_name) {
		this.association_name = association_name;
	}
	public String getAssociation_description() {
		return association_description;
	}
	public void setAssociation_description(String association_description) {
		this.association_description = association_description;
	}
	public Date getAssociation_fondationDate() {
		return association_fondationDate;
	}
	public void setAssociation_fondationDate(Date association_fondationDate) {
		this.association_fondationDate = association_fondationDate;
	}
	public int getAssociation_customersNumber() {
		return association_customersNumber;
	}
	public void setAssociation_customersNumber(int association_customersNumber) {
		this.association_customersNumber = association_customersNumber;
	}

	
}
