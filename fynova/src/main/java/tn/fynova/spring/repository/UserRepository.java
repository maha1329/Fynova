package tn.fynova.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.fynova.spring.entities.Role;
import tn.fynova.spring.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	@Query("SELECT COUNT(a) FROM User a WHERE a.user_role= :role ")
	int NbrCustmer(@Param("role") Role role);

	

List<User> findByFirstname(String username);
	
	@Query("Select NEW tn.fynova.spring.entities.User(association_fiscalNumber,association_name,association_description,association_fondationDate,  association_customersNumber) FROM User")
	List<User> retrieveAllAssociations();
	
	@Query("Select NEW tn.fynova.spring.entities.User(association_fiscalNumber) FROM User us where us.association_fiscalNumber=:fisc ")
	User retrieveAssociationById(@Param("fisc")int association_fiscalNumber);
	
	
	@Modifying
	@Query("delete User u where u.association_fiscalNumber=:fisc")
	void deleteAssociationById(@Param("fisc")int association_fiscalNumber);

}
