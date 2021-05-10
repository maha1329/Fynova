package tn.fynova.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.fynova.spring.entities.Project;
@Repository
public interface ProjectRepository  extends JpaRepository<Project,Integer>{
	
	@Query(value="SELECT u FROM Project u WHERE u.projectname= ?1")
	Optional<Project> findProjectByName(String name);
	
	@Query(value="SELECT u FROM Project u WHERE u.user_project.user_id= ?1")
	List<Project> findProjectByIdUser(int i);
		
	List<Project> findAllByOrderByProjectcost();
	
	List<Project> findByCategorie(String s);
	
	long countByCategorie(String name);
	
	List<Project> findByStatus(int i);



}
