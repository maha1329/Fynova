package tn.fynova.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.fynova.spring.entities.Credit;
@Repository
public interface CreditRepository extends CrudRepository<Credit,Integer>{

}
