package tn.fynova.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.fynova.spring.entities.LogCredit;


@Repository
public interface LogCreditRepository extends CrudRepository<LogCredit,Integer>{

}
