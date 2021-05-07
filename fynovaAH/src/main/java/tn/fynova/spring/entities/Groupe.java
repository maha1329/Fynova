package tn.fynova.spring.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;


@Entity
public class Groupe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int groupeid;
	private double amount;
	private Date datedeadline;
	private double chakaka;
	private double interet;
	
	@ManyToMany(mappedBy="groupes",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private List<User> users;
	
	
	
	
	public Groupe() {
		super();
	}

	public Groupe(int groupeid, double amount, double chakaka) {
		super();
		this.groupeid = groupeid;
		this.amount = amount;
		this.chakaka = chakaka;
	}
	
	public double getChakaka() {
		return chakaka;
	}
	public void setChakaka(double chakaka) {
		this.chakaka = chakaka;
	}
	public double getInteret() {
		return interet;
	}
	public void setInteret(double interet) {
		this.interet = interet;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public int getGroupeid() {
		return groupeid;
	}
	public void setGroupeid(int groupeid) {
		this.groupeid = groupeid;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getDatedeadline() {
		return datedeadline;
	}
	public void setDatedeadline(Date datedeadline) {
		this.datedeadline = datedeadline;
	}
	

}
