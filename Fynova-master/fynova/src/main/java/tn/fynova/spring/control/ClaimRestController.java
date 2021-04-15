package tn.fynova.spring.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.fynova.spring.entities.Claim;
import tn.fynova.spring.service.IClaimService;

@RestController
@RequestMapping("/claim")
public class ClaimRestController {

	
	@Autowired
    IClaimService servclaim;
	
	
	
	
	
	
	@GetMapping("/allclaims")
	public List<Claim> Getallclaims()
	{
		return servclaim.retrieveAllClaims();
	}
	@PostMapping("/addclaims/{userid}")
	//@PreAuthorize("hasAuthority('investor') or hasAuthority('customer')")
	public Claim Addclaim(@RequestBody Claim c,@PathVariable("userid")int userid  )
	{
		return servclaim.addClaim(c,userid);
	
	}
	@DeleteMapping("/remove/{idclaim}")
	public void DeleteClaim(@PathVariable("idclaim") int id) 
	{
		servclaim.deleteClaim(id);
	}
	@GetMapping("/retrieveclaims/{idclaim}")
			public Claim RetrieveClaim(@PathVariable("idclaim")int id )
			{
		return servclaim.retrieveClaims(id);
			}
	
	
	
	@PutMapping("/reponseclaim/{idclaim}")
		public void reponseclaim(@PathVariable("idclaim") int id)
		{
			servclaim.reponseclaim(id);
		}
	@PutMapping("/updateclaim/{idclaim}")
	public void updateclaim(@RequestBody Claim c,@PathVariable("idclaim") int id)
	{
		servclaim.updateclaim(c, id);
	}
	
	
	@GetMapping("/triclaims")
		public List<Claim> triClaims ()
		{
			return servclaim.triClaims();
		}
	@GetMapping("/claimsbyuser/{iduser}")
	public List<Claim> retrieveClaimsByuser (@PathVariable("iduser") int id)
	{
		return servclaim.retrieveClaimsByuser(id);
	}
	@GetMapping("/statnbclaims")
	public ResponseEntity<?> StatNBClaims()
	{
		return servclaim.StatNBClaims();
	}
	
	@GetMapping("/StatTempReponseEmployer")
	public ResponseEntity<?> StatTempReponseEmployer()
	{
		return servclaim.StatTempReponseEmployer();
	}
	
	
	
	
}
