package tn.fynova.spring.service;

import java.io.IOException;

import java.util.List;
import java.util.Optional;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
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
	public int ajouterProject(Project p) {
		projectRepository.save(p);
		return (int) p.getProjectid();
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
	public List<Project> retrieveAllProjectsmin() {
		List<Project> projects = (List<Project>) projectRepository.findAllByOrderByProjectcost();		
		return projects;
	}
	
	@Override
	public 	List<Project> retrieveAllProjectsCat(String s){
		List<Project> p = (List<Project>) projectRepository.findByCategorie(s);
		return p;
	}
	
	@Override
	public List<Project> retrieveAllProjectsIdUser(int i){
		List<Project> p = (List<Project>) projectRepository.findProjectByIdUser(i);
		return p;
	}

	//get the project by his ID
	@Override
	public Optional<Project> retrieveProject(int id) {
		Optional<Project> project = projectRepository.findById(id);
		return project;
	}
	
	@Override
	public Optional<Project> retrieveProjectsByName(String name){
		Optional<Project> t  = projectRepository.findProjectByName(name);
		return t ;
	}
	
	@Override
	public long totcountByCategorie(String type) {
		
		return projectRepository.countByCategorie(type);
	}
	
	@Override
	public List<Project> findAllByStatus(int i){
		List<Project> p = (List<Project>) projectRepository.findByStatus(i);
		return p;
	}
	
	//change status to 1 == activer le projet == approuver la demande de credit
	@Override
	public Optional<Project> changeStatusTo1ById(int id) {
		Optional<Project> p=projectRepository.findById(id);
		p.get().setStatus(1);
		return p;
	}
	
	//change status to 0
	@Override
	public Optional<Project> changeStatusTo0ById(int id) {
		Optional<Project> p=projectRepository.findById(id);
		p.get().setStatus(0);
		return p;
	}
	//generate pdf
	@Override
	public void generatePDF() throws IOException {
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage(page);

		PDPageContentStream contentStream = new PDPageContentStream(document, page);

		contentStream.setFont(PDType1Font.COURIER, 12);
		contentStream.beginText();
		contentStream.showText("Hello World");
		contentStream.endText();
		contentStream.close();

		document.save("pdfBoxHelloWorld.pdf");
		document.close();
	}
	
	
	
	

}
