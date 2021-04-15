package tn.fynova.spring.service;

import java.util.List;
import java.util.Optional;

import tn.fynova.spring.entities.Project;

public interface IProjectService {
	Project addProject(Project e);
	void deleteProject(int id);
	List<Project> retrieveAllProjects();
	Optional<Project>  retrieveProject(int id);
}
