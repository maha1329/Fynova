package tn.fynova.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.fynova.spring.entities.Account;
import tn.fynova.spring.entities.User;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
    @Query("SELECT a FROM Account a WHERE a.account_user= :user ")
    Account AccountByUser(@Param("user") User user);

    @Query("SELECT COUNT(u) FROM Account u WHERE u.Status=:status")
    int NbrStatus (@Param("status") String status);
    
    //@Query("select count(c) from Claim c join c.claim_user u join u.user_accounts a where a.Status Like CONCAT('%',:type,'%')  ")
    //int nbClaimtypestable(@Param("type") String type);
}
