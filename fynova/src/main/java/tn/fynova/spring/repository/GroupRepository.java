package tn.fynova.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.fynova.spring.entities.Groupe;

@Repository
public interface GroupRepository extends JpaRepository<Groupe,Integer>{ 

}
