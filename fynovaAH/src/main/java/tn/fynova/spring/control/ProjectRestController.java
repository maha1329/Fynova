package tn.fynova.spring.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.fynova.spring.entities.Project;
import tn.fynova.spring.repository.ProjectRepository;
import tn.fynova.spring.service.FileUploadService;
import tn.fynova.spring.service.ProjectServiceImpl;
import tn.fynova.spring.service.UserServiceImpl;

@RestController
@RequestMapping("/project")
public class ProjectRestController {
	
	@Autowired
	ProjectServiceImpl projectservice;
	
	@Autowired
	UserServiceImpl userservice;
	
	@Autowired
	ProjectRepository projectrepository;
	
	@Autowired
	FileUploadService fileUploadService;
	
	@Autowired
	MailingController mailing;
	
	@GetMapping("/all")
	@ResponseBody
	public List<Project> getProject (){

	List<Project> list =  projectservice.retrieveAllProjects();
	return list;
	}
	
	@GetMapping("/allmincost")
	@ResponseBody
	public List<Project> getProjectmin (){
	List<Project> list =  projectservice.retrieveAllProjectsmin();
	return list;
	}
	
	@GetMapping("/show/{id}")
	@ResponseBody
	public Optional<Project>  getProject (@PathVariable("id")int id){
	  Optional<Project> t = projectservice.retrieveProject(id);
	  return t;
	}
	
	//getting projects with their name
	@GetMapping("/showme/{name}")
	@ResponseBody
	public Optional<Project> retrieveProjectsByName(@PathVariable("name") String name){
		Optional<Project> t = projectrepository.findProjectByName(name);
		return t;
	}
	
	//getting projects with their categories
	@GetMapping("/showmecat/{cat}")
	@ResponseBody
	public 	List<Project> retrieveAllProjectsCat(@PathVariable("cat")String cat){
		List<Project> p = (List<Project>) projectservice.retrieveAllProjectsCat(cat);
		return p;
	}
	
	//getting projects with their IDusers
	@GetMapping("/alluser/{i}")
	@ResponseBody
	public 	List<Project> retrieveAllProjectsIdUser(@PathVariable("i")int i){
		List<Project> p = (List<Project>) projectservice.retrieveAllProjectsIdUser(i);
		return p;
	}
	
	//uploading FILE
	@PostMapping("/upload")
	@ResponseBody
	public String uploadOneFile() {
		fileUploadService.upload();
		return "success";
	}
	
	
	@PostMapping("/add/{categorie}/{projectcost}/{projectname}")
	@ResponseBody
	public Project addProject(@PathVariable("categorie") String categorie,@PathVariable("projectcost") float projectcost,@PathVariable("projectname") String projectname) {
		Project e=new Project();
		e.setCategorie(categorie);
		e.setProjectcost(projectcost);
		e.setProjectname(projectname);
		Project project = projectservice.addProject(e);	
		//mailing.sendSimpleEmail();//envoyer un mail demande en cours de traitement 
		return project;
	}
	
	@PostMapping("/addapprouved/{id}")
	@ResponseBody
	public Project addProjectApprouved(@PathVariable("id") int id) {
		projectservice.retrieveProject(id).get().setStatus(1);
		mailing.sendSimpleEmail();//envoyer un mail demande en cours de traitement 
		return projectservice.retrieveProject(id).get();
	}
	
	
	
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public String deleteProject(@PathVariable("id")int id) {
		 projectservice.deleteProject(id);	
		 return "done";
	}
	
	//counting the nombre of projects in eaach category for statistics 
	@GetMapping("/class/{type}")
	@ResponseBody
	public long totcountByCategorie(@PathVariable("type")String type) {
		
		return projectservice.totcountByCategorie(type);
	}
	
	//getting by status
	@GetMapping("/projectapprouved/{i}")
	@ResponseBody
	public List<Project> findAllProjectByStatus(@PathVariable("i")int i){
		List<Project> p = projectservice.findAllByStatus(i);
		return p;
	}
	
	
	

}
