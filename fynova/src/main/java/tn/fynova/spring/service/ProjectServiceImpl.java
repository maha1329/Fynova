package tn.fynova.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.fynova.spring.entities.Project;
import tn.fynova.spring.repository.ProjectRepository;
@Service
@Transactional

public class ProjectServiceImpl implements IProjectService {
	@Autowired
	ProjectRepository projectRepository;
	
	@Override
	public Project addProject(Project e) {
		Project project = projectRepository.save(e);	
		return project;
	}

	@Override
	public void deleteProject(int id) {
		 projectRepository.deleteById(id);	
	}

	@Override
	public List<Project> retrieveAllProjects() {
		List<Project> projects = (List<Project>) projectRepository.findAll();		
		return projects;
	}

	@Override
	public Optional<Project> retrieveProject(int id) {
		Optional<Project> project = projectRepository.findById(id);
		return project;
	}

}
