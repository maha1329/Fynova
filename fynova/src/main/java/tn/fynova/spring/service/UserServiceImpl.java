package tn.fynova.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.fynova.spring.entities.User;
import tn.fynova.spring.repository.UserRepository;

@Service
@Transactional

public class UserServiceImpl implements IUserService {
	@Autowired
	UserRepository userRepository;
	
	@Override
	public User addUser(User e) {
		User user = userRepository.save(e);
		return user;
	}

	@Override
	public void deleteUser(int id) {
		userRepository.deleteById(id);
		
	}

	@Override
	public List<User> retrieveAllUsers() {
		List<User> users = (List<User>) userRepository.findAll();
		return users;
	}

	@Override
	public Optional<User> retrieveUser(int id) {
		Optional<User> user = userRepository.findById(id);
		return user;
	}

	@Override
	public List<User> retrieveAllAssociations() {
		List<User> list = userRepository.retrieveAllAssociations();
		return list;
	}

	@Override
	public User retrieveAssociationById(int association_fiscalNumber) {
		User u = userRepository.retrieveAssociationById(association_fiscalNumber);
		return u;
	}

	@Override
	public void deleteAssociationById(int association_fiscalNumber) {
		userRepository.deleteAssociationById(association_fiscalNumber);		
	}



}
