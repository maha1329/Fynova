package tn.fynova.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.fynova.spring.entities.Account;
import tn.fynova.spring.entities.Role;
import tn.fynova.spring.entities.Transaction;
import tn.fynova.spring.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	@Query("SELECT COUNT(a) FROM User a WHERE a.user_role= :role ")
	int NbrCustmer(@Param("role") Role role);

	List<User> findByFirstname(String username);

}
