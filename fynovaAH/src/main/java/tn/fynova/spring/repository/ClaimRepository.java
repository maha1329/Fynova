package tn.fynova.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.fynova.spring.entities.Claim;



@Repository

public interface ClaimRepository extends JpaRepository<Claim, Integer> {
	
	
	@Query("Select count(c) from Claim c ")
	long nbclaims();
  @Query("select count(c) from Claim c join c.claim_user u join u.user_accounts a where a.Status Like CONCAT('%',:type,'%')  ")
  long nbClaimtyperisque(@Param("type") String type);
  @Query("select count(c) from Claim c join c.claim_user u join u.user_accounts a where a.Status Like CONCAT('%',:type,'%')  ")
  long nbClaimtypestable(@Param("type") String type);
	
  @Query("SELECT DATEDIFF(c.dateclaim,c.datereponse) FROM Claim c where c.claim_id=:idc and c.status like 'traitee'")
  int tempreponseclaim(@Param("idc") int idc);
  @Query("SELECT DATEDIFF(c.dateclaim,c.datereponse) FROM Claim c where  c.claim_id=:idc and c.status like 'traitee'")
  int temptotalereponse(@Param("idc") int idc);
  
  
}
 