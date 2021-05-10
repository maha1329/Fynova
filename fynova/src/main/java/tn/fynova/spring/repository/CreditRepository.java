package tn.fynova.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.fynova.spring.entities.Account;
import tn.fynova.spring.entities.Credit;
@Repository
public interface CreditRepository extends CrudRepository<Credit,Integer>{
	/*
	 * @Query("SELECT a FROM Credit a WHERE a.credit_account= :account ") Credit
	 * CreditByAccount(@Param("account") Account account);
	 */
}
