package tn.fynova.spring.service;

import java.util.List;
import java.util.Optional;

import tn.fynova.spring.entities.User;

public interface IUserService {
	User addUser(User e);

	void deleteUser(int id);

	List<User> retrieveAllUsers();

	Optional<User> retrieveUser(int id);

	// AssociationModule

	List<User> retrieveAllAssociations();

	User retrieveAssociationById(int association_fiscalNumber);

	void deleteAssociationById(int association_fiscalNumber);

}
