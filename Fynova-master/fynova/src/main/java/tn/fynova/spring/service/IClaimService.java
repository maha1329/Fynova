package tn.fynova.spring.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import tn.fynova.spring.entities.Claim;

public interface IClaimService {
	

	Claim addClaim(Claim c,int  userid);
	void deleteClaim(int id);
	List<Claim> retrieveAllClaims();
	Claim retrieveClaims(int id);
	void reponseclaim(int id_claim);
	List<Claim> triClaims();
	ResponseEntity<?> StatNBClaims();
	List<Claim> retrieveClaimsByuser(int iduser);
	void updateclaim(Claim c,int iduser);
	ResponseEntity<?> StatTempReponseEmployer();
	
	
	 

}
