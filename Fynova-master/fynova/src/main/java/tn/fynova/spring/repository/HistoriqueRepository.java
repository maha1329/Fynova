package tn.fynova.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.fynova.spring.entities.Historique;

@Repository
public interface HistoriqueRepository extends CrudRepository<Historique,Integer> {
}
