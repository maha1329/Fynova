package tn.fynova.spring.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class User implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;
	
	private String user_password;
	
	private String user_email;
	
	private String user_phone;
	
	private String firstname;
	
	private String user_lastname;
	
	private int user_cin;
	
	@Enumerated
	private Grade employee_grade;
	
	@Temporal(TemporalType.DATE)
	private Date user_birthday;
	
	@Enumerated(EnumType.STRING)
	private Role user_role;
	
    private String job;
	
	@Enumerated(EnumType.STRING)
	private Origin customer_origin;

	private int association_fiscalNumber;
	
	private String association_name;
	
	private String association_description;
	
	@Temporal(TemporalType.DATE)
	private Date association_fondationDate;
	
	private int association_customersNumber;
	 
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="claim_user")
	private List<Claim> user_claims;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="accountuser")
	private List<Account> user_accounts;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="user_project")
	private List<Project> user_projects;
	
	
	@ManyToMany
	private List<Groupe> groupes;
	
	private int accordGroup; 
	
	public List<Groupe> getGroupes() {
		return groupes;
	}
	public void setGroupes(List<Groupe> groupes) {
		this.groupes = groupes;
	}
	public int getAccordGroup() {
		return accordGroup;
	}
	public void setAccordGroup(int accordGroup) {
		this.accordGroup = accordGroup;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public List<Claim> getUser_claims() {
		return user_claims;
	}
	public void setUser_claims(List<Claim> user_claims) {
		this.user_claims = user_claims;
	}
	public List<Account> getUser_accounts() {
		return user_accounts;
	}
	public void setUser_accounts(List<Account> user_accounts) {
		this.user_accounts = user_accounts;
	}
	public List<Project> getUser_projects() {
		return user_projects;
	}
	public void setUser_projects(List<Project> user_projects) {
		this.user_projects = user_projects;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	
	public String getUser_lastname() {
		return user_lastname;
	}
	public void setUser_lastname(String user_lastname) {
		this.user_lastname = user_lastname;
	}
	public int getUser_cin() {
		return user_cin;
	}
	public void setUser_cin(int user_cin) {
		this.user_cin = user_cin;
	}
	public Grade getEmployee_grade() {
		return employee_grade;
	}
	public void setEmployee_grade(Grade employee_grade) {
		this.employee_grade = employee_grade;
	}
	public Date getUser_birthday() {
		return user_birthday;
	}
	public void setUser_birthday(Date user_birthday) {
		this.user_birthday = user_birthday;
	}
	public Role getUser_role() {
		return user_role;
	}
	public void setUser_role(Role user_role) {
		this.user_role = user_role;
	}

	public void setUser_claims(ArrayList<Claim> user_claims) {
		this.user_claims = user_claims;
	}
	
	public void setUser_accounts(ArrayList<Account> user_accounts) {
		this.user_accounts = user_accounts;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Origin getCustomer_origin() {
		return customer_origin;
	}
	public void setCustomer_origin(Origin customer_origin) {
		this.customer_origin = customer_origin;
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
	@Override
	public String toString() {
		return "User [association_fiscalNumber=" + association_fiscalNumber + ", association_name=" + association_name
				+ ", association_description=" + association_description + ", association_fondationDate="
				+ association_fondationDate + ", association_customersNumber=" + association_customersNumber + "]";
	}
	public User(int association_fiscalNumber, String association_name, String association_description,
			Date association_fondationDate, int association_customersNumber) {
		super();
		this.association_fiscalNumber = association_fiscalNumber;
		this.association_name = association_name;
		this.association_description = association_description;
		this.association_fondationDate = association_fondationDate;
		this.association_customersNumber = association_customersNumber;
	}
	
	public User(int association_fiscalNumber) {
		super();
		this.association_fiscalNumber = association_fiscalNumber;
	}
	public User() {
		super();
	}
	public User(int user_id, String user_password, String user_email, String user_phone, String firstname,
			String user_lastname, int user_cin, Grade employee_grade, Date user_birthday, Role user_role, String job,
			Origin customer_origin, int association_fiscalNumber, String association_name,
			String association_description, Date association_fondationDate, int association_customersNumber,
			List<Claim> user_claims, List<Account> user_accounts, List<Project> user_projects, List<Groupe> groupes,
			int accordGroup) {
		super();
		this.user_id = user_id;
		this.user_password = user_password;
		this.user_email = user_email;
		this.user_phone = user_phone;
		this.firstname = firstname;
		this.user_lastname = user_lastname;
		this.user_cin = user_cin;
		this.employee_grade = employee_grade;
		this.user_birthday = user_birthday;
		this.user_role = user_role;
		this.job = job;
		this.customer_origin = customer_origin;
		this.association_fiscalNumber = association_fiscalNumber;
		this.association_name = association_name;
		this.association_description = association_description;
		this.association_fondationDate = association_fondationDate;
		this.association_customersNumber = association_customersNumber;
		this.user_claims = user_claims;
		this.user_accounts = user_accounts;
		this.user_projects = user_projects;
		this.groupes = groupes;
		this.accordGroup = accordGroup;
	}
	
}
