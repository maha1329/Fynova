package tn.fynova.spring.control;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.fynova.spring.entities.Association;
import tn.fynova.spring.entities.Transaction;
import tn.fynova.spring.entities.User;
import tn.fynova.spring.repository.UserRepository;
import tn.fynova.spring.service.UserServiceImpl;

@RestController
@RequestMapping("/association")

public class AssociationRestController {
	@Autowired
	UserServiceImpl userserviceimpl;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/showAllAssociations")
	public List<User> retrieveAllAssociations() {
		List<User> list = userRepository.retrieveAllAssociations();
		return list;
	}
	
	@GetMapping("/{id}")
	public User retrieveAssociationById (@PathVariable("id") int association_fiscalNumber){
		User u = userRepository.retrieveAssociationById(association_fiscalNumber);
		return u;
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteAssociationById(@PathVariable("id") int association_fiscalNumber) {
		userRepository.deleteAssociationById(association_fiscalNumber);
		
	}
	
}
