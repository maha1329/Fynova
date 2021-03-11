package tn.fynova.spring.service;

import java.util.List;
import java.util.Optional;

import tn.fynova.spring.entities.Claim;

public interface IClaimService {
	

	Claim addClaim(Claim c);

	void deleteClaim(int id);
	List<Claim> retrieveAllClaims();

	Optional<Claim> retrieveClaims(int id);

}
