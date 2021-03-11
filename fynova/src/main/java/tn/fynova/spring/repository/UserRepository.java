package tn.fynova.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.fynova.spring.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}
