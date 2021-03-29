package tn.fynova.spring.entities;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Customer {
	
	private String customer_job;
	
	@Enumerated(EnumType.STRING)
	private Origin customer_origin;
	
}
