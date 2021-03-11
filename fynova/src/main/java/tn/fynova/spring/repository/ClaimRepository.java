package tn.fynova.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.fynova.spring.entities.Claim;



@Repository

public interface ClaimRepository extends CrudRepository<Claim, Integer> {

}
