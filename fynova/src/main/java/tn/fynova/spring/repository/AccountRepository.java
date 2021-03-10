package tn.fynova.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.fynova.spring.entities.Account;
import tn.fynova.spring.entities.User;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

}
