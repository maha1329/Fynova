package tn.fynova.spring.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.fynova.spring.entities.Demand;


@Repository
public interface DemandRepository extends CrudRepository<Demand,Integer> {



}
