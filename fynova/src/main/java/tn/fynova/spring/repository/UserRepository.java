package tn.fynova.spring.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.fynova.spring.entities.Association;
import tn.fynova.spring.entities.Transaction;
import tn.fynova.spring.entities.User;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {


	List<User> findByFirstname(String username);
	
	@Query("Select NEW tn.fynova.spring.entities.User(association_fiscalNumber,association_name,association_description,association_fondationDate,  association_customersNumber) FROM User")
	List<User> retrieveAllAssociations();
	
	@Query("Select NEW tn.fynova.spring.entities.User(association_fiscalNumber) FROM User us where us.association_fiscalNumber=:fisc ")
	User retrieveAssociationById(@Param("fisc")int association_fiscalNumber);
	
	
	@Modifying
	@Query("delete User u where u.association_fiscalNumber=:fisc")
	void deleteAssociationById(@Param("fisc")int association_fiscalNumber);
	
	
}
