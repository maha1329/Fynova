package tn.fynova.spring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.fynova.spring.control.TwilioController;
import tn.fynova.spring.entities.Account;
import tn.fynova.spring.entities.Claim;
import tn.fynova.spring.entities.User;
import tn.fynova.spring.repository.ClaimRepository;
import tn.fynova.spring.repository.UserRepository;

@Service
@Transactional

public class ClaimServiceImpl implements IClaimService {
	@Autowired
	ClaimRepository claimRepository;
	@Autowired
	UserRepository userrep;
	@Autowired
	TwilioController twilioCon;

	@Override
	public Claim addClaim(Claim c, int userid) {

		User claim_user = userrep.findById(userid).orElse(null);

		Claim claim = new Claim();
		claim.setClaim_user(claim_user);
		claim.setStatus("non traite");
		claim.setDateclaim(new Date());
		claim.setClaim_description(c.getClaim_description());
		claim.setClaim_title(c.getClaim_title());
		claimRepository.save(claim);

		return claim;

	}

	@Override
	public void deleteClaim(int id) {
		claimRepository.deleteById(id);
	}

	@Override
	public List<Claim> retrieveAllClaims() {
		List<Claim> claims = (List<Claim>) claimRepository.findAll();
		return claims;
	}

	@Override
	public Claim retrieveClaims(int id) {
		Claim claim = claimRepository.findById(id).orElse(null);
		return claim;
	}

	@Override
	public void reponseclaim(int id_claim) {
		// TODO Auto-generated method stub

		Claim claim = claimRepository.findById(id_claim).orElse(null);

		claim.setStatus("traitee");
		claim.setDatereponse(new Date());
		// twilioCon.sendotp("+216"+claim.getClaim_user().getUser_phone());

	}

	@Override
	public List<Claim> triClaims() {
		// TODO Auto-generated method stub
		List<Claim> claims = (List<Claim>) claimRepository.findAll();
		List<Claim> triclaims = new ArrayList<>();

		for (Claim claim : claims) {
			for (Account acount : claim.getClaim_user().getUser_accounts()) {
				if (acount.getStatus().equals("a risque"))

				{
					triclaims.add(claim);
				}

			}
		}

		return triclaims;
	}

	@Override
	public ResponseEntity<?> StatNBClaims() {
		// TODO Auto-generated method stub
		List<Claim> claims = (List<Claim>) claimRepository.findAll();

		String response = "";

		float nbclaim = claimRepository.nbclaims();
		float nbclaimrisque = claimRepository.nbClaimtyperisque("risque");
		float nbclaimstable = claimRepository.nbClaimtypestable("stable");

       System.out.println("aaaaaaa aaa  aaaa rani nbclaim");
       
       System.out.println(nbclaim);
       System.out.println(" nbclaim par statut arisque ");
       System.out.println(nbclaimrisque);
		float moyrisque =((nbclaimrisque/ nbclaim)/ nbclaim ) *100;
		 System.out.println("le pourcentage de claims pour statut a risque ");
	       System.out.println(moyrisque);
	   	float moystable =((nbclaimstable/ nbclaim)/ nbclaim )* 100;
		 System.out.println("le pourcentage de claims pour statut stable ");
	       System.out.println(moyrisque);
	  	 System.out.println(" nbclaim par statut stable ");
	       System.out.println(nbclaimstable);
	       
	       
		response = "le pourcentage de la clientèle à risques est :" + moyrisque+"%"+
		
				"******le pourcentage de la clientèle  stable est :"+moystable+"%"
				;
 
		return ResponseEntity.ok(response);
	}

	@Override
	public List<Claim> retrieveClaimsByuser(int iduser) {
		// TODO Auto-generated method stub
		User claim_user = userrep.findById(iduser).orElse(null);
		List<Claim> claimsuser = new ArrayList<>();
		for (Claim claim : claim_user.getUser_claims()) {
			claimsuser.add(claim);
		}

		return claimsuser;
	}

	@Override
	public void updateclaim(Claim c, int id_claim) {
		// TODO Auto-generated method stub
		Claim claimToupdate = claimRepository.findById(id_claim).orElse(null);

		claimToupdate.setClaim_description(c.getClaim_description());
		claimToupdate.setClaim_title(c.getClaim_title());

	}

	@Override
	public ResponseEntity<?> StatTempReponseEmployer() {
		// TODO Auto-generated method stub

		String response = "";

		float nbtotale=0;
		List<Claim> claims = (List<Claim>) claimRepository.findAll();
		for (Claim claim : claims) {
			if(claim.getStatus().equals("traitee"))
			{
			nbtotale=claimRepository.temptotalereponse(claim.getClaim_id())+nbtotale;
			}

		}
		for (Claim claim : claims) {
			
			if(claim.getStatus().equals("traitee"))
			{
			float	nb=claimRepository.tempreponseclaim(claim.getClaim_id());
			
			response=response+"   le temp de reponse  de    " +claim.getClaim_user().getFirstname()+"      est    " +(nb/nbtotale)*100+"    %    "
					;
			}
		}
		
	
		
		
		
		
		

		return ResponseEntity.ok(response);	
		
	}

}
