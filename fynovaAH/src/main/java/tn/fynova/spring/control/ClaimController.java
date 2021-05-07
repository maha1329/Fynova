package tn.fynova.spring.control;

import java.util.Date;
import java.util.List;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.fynova.spring.entities.Claim;
import tn.fynova.spring.entities.User;
import tn.fynova.spring.service.IClaimService;
import tn.fynova.spring.service.statclaims;

@Controller(value = "claimController") // Name of the bean in Spring IoC
@ELBeanName(value = "claimController") // Name of the bean used by JSF
public class ClaimController {

	@Autowired
	IClaimService claimserv;
	@Autowired
	statclaims stat;
	
	public statclaims getStat() {
		return stat;
	}
	public void setStat(statclaims stat) {
		this.stat = stat;
	}






	private int claim_id;
	private String claim_title;
	private String  claim_description;
	private String status;
	private Date dateclaim;
	private Date datereponse;
	private User claim_user;
	private  int userid=1;
	private List<Claim> claims;
    private int claimIdToUpdate;


public int getClaimIdToUpdate() {
		return claimIdToUpdate;
	}
	public void setClaimIdToUpdate(int claimIdToUpdate) {
		this.claimIdToUpdate = claimIdToUpdate;
	}
public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}

IClaimService getClaimserv() {
		return claimserv;
	}
	public void setClaimserv(IClaimService claimserv) {
		this.claimserv = claimserv;
	}
	public int getClaim_id() {
		return claim_id;
	}
	public void setClaim_id(int claim_id) {
		this.claim_id = claim_id;
	}
	public String getClaim_title() {
		return claim_title;
	}
	public void setClaim_title(String claim_title) {
		this.claim_title = claim_title;
	}
	public String getClaim_description() {
		return claim_description;
	}
	public void setClaim_description(String claim_description) {
		this.claim_description = claim_description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDateclaim() {
		return dateclaim;
	}
	public void setDateclaim(Date dateclaim) {
		this.dateclaim = dateclaim;
	}
	public Date getDatereponse() {
		return datereponse;
	}
	public void setDatereponse(Date datereponse) {
		this.datereponse = datereponse;
	}
	public User getClaim_user() {
		return claim_user;
	}
	public void setClaim_user(User claim_user) {
		this.claim_user = claim_user;
	}
	
	
	public void updateClaimJsf()throws Exception {  
         Claim c=new Claim(claimIdToUpdate,claim_title,claim_description);
         claimserv.updateclaim(c, claimIdToUpdate);
         ResetClaimForm();
	}
	
	public void addClaimJsf()throws Exception {
	 //   SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");  
	//    Date date1=formatter1.parse(dateclaim);  
         Claim c=new Claim(claim_title,claim_description);
         claimserv.addClaim(c,userid);
         ResetClaimForm();
	}
	public void removeclaim(String claim_id) {
        claimserv.deleteClaim(Integer.valueOf(claim_id));     
		}
	public	Claim retrieveALLClaim(int claim_id)
	{
		return claimserv.retrieveClaims(claim_id) ;
	}
	

public	List<Claim> retrieveClaimsByuser()
	
	{
		return claimserv.retrieveClaimsByuser(userid) ;

	}

	public List<Claim> getClaims() {
		claims = claimserv.retrieveAllClaims(); 
	return claims;
	} 
	public void setClaims(List<Claim> claims) {
		this.claims = claims;
	}
	public List<Claim> trieClaimsbystatuts(){
		return claimserv.triClaims();
	}

	public void reponseclaim(String claim_id)
	{
		claimserv.reponseclaim(Integer.valueOf(claim_id));
	}
	
	

	
public void displayClaim(Claim claim) {
	    this.setClaim_description(claim.getClaim_description());
	    this.setClaim_title(claim.getClaim_title());
	    this.setClaimIdToUpdate(claim.getClaim_id());
	}

	public void ResetClaimForm() {
		this.setClaim_description(null);
		this.setClaim_title(null);


	}
	
	
	
	
	
	
	public void  statsstiqueclaim()
	{
		 stat.createDonutModel();
	}
	
	
	
	
	
	
}
