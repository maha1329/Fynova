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
public class Project implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int project_id;
	private String project_name;
	private String project_description;
	private float project_cost;
	
	@Temporal(TemporalType.DATE)
	private Date project_date;

	@ManyToOne
	private User user_project;
	
	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getProject_description() {
		return project_description;
	}

	public void setProject_description(String project_description) {
		this.project_description = project_description;
	}

	public float getProject_cost() {
		return project_cost;
	}

	public void setProject_cost(float project_cost) {
		this.project_cost = project_cost;
	}

	public Date getProject_date() {
		return project_date;
	}

	public void setProject_date(Date project_date) {
		this.project_date = project_date;
	}
	
	
	

}
