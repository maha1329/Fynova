package tn.fynova.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.fynova.spring.entities.Project;
@Repository
public interface ProjectRepository  extends CrudRepository<Project,Integer>{

}
