package tn.fynova.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.fynova.spring.entities.Project;
@Repository
public interface ProjectRepository  extends JpaRepository<Project,Integer>{

	//List<Project> findByProjectnameOrderByProjectdateAsc(String name);
	
}
