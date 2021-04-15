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
	private int projectid;
	private String projectname;
	private String projectdescription;
	private float projectcost;
	
	@Temporal(TemporalType.DATE)
	private Date projectdate;

	@ManyToOne
	private User user_project;
	
	
	public int getProjectid() {
		return projectid;
	}

	public void setProjectid(int projectid) {
		this.projectid = projectid;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getProjectdescription() {
		return projectdescription;
	}

	public void setProjectdescription(String projectdescription) {
		this.projectdescription = projectdescription;
	}

	public float getProjectcost() {
		return projectcost;
	}

	public void setProjectcost(float projectcost) {
		this.projectcost = projectcost;
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
