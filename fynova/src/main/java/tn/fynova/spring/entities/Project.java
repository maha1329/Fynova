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
	private Date projectdate;

	@ManyToOne
	private User user_project;
	
	
	public int getProjectid() {
		return project_id;
	}

	public void setProjectid(int projectid) {
		this.project_id = projectid;
	}

	public String getProjectname() {
		return project_name;
	}

	public void setProjectname(String projectname) {
		this.project_name = projectname;
	}

	public String getProjectdescription() {
		return project_description;
	}

	public void setProjectdescription(String projectdescription) {
		this.project_description = projectdescription;
	}

	public float getProjectcost() {
		return project_cost;
	}

	public void setProjectcost(float projectcost) {
		this.project_cost = projectcost;
	}

	public Date getProjectdate() {
		return projectdate;
	}

	public void setProjectdate(Date projectdate) {
		this.projectdate = projectdate;
	}

	public User getUser_project() {
		return user_project;
	}

	public void setUser_project(User user_project) {
		this.user_project = user_project;
	}
	
	
	

}
