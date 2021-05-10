package tn.fynova.spring.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


import tn.fynova.spring.entities.Project;

public interface IProjectService {
	public int ajouterProject(Project p);
	Project addProject(Project e);
	void deleteProject(int id);
	List<Project> retrieveAllProjects();
	List<Project> retrieveAllProjectsmin();
	List<Project> retrieveAllProjectsCat(String s);
	List<Project> retrieveAllProjectsIdUser(int i);
	Optional<Project>  retrieveProject(int id);
	Optional<Project> retrieveProjectsByName(String name);
	long totcountByCategorie(String name);
	List<Project> findAllByStatus(int i);
	Optional<Project> changeStatusTo1ById(int id);
	Optional<Project> changeStatusTo0ById(int id);
	void generatePDF()throws IOException;
	//void createDonutModel();
}
