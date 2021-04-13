package tn.fynova.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.fynova.spring.entities.Transaction;
import tn.fynova.spring.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {


	List<User> findByFirstname(String username);

}
