package tn.fynova.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import tn.fynova.spring.entities.Claim;
import tn.fynova.spring.repository.ClaimRepository;

public class ClaimServiceImpl implements IClaimService {
	@Autowired
	ClaimRepository claimRepository;
	
	@Override
	public Claim addClaim(Claim c) {
		Claim claim = claimRepository.save(c);
		return claim;
	}

	@Override
	public void deleteClaim(int id) {
		claimRepository.deleteById(id);		
	}
	@Override
	public List<Claim> retrieveAllClaims() {
		List<Claim> claims = (List<Claim>)claimRepository.findAll();
		return claims;
	}

	@Override
	public Optional<Claim> retrieveClaims(int id) {
		Optional<Claim>  claim = claimRepository.findById(id);
		return claim;
	}

}
