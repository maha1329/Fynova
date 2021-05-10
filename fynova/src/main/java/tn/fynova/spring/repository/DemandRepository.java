package tn.fynova.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.fynova.spring.entities.Demand;
import tn.fynova.spring.entities.Status;


@Repository
public interface DemandRepository extends CrudRepository<Demand,Integer> {

    List<Demand> findAllByStatus(Status inprogress);
    
}
