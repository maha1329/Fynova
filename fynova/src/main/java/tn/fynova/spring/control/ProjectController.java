package tn.fynova.spring.control;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import tn.fynova.spring.entities.Credit;
import tn.fynova.spring.entities.Operation;
import tn.fynova.spring.entities.Project;
import tn.fynova.spring.entities.Transaction;
import tn.fynova.spring.repository.CreditRepository;
import tn.fynova.spring.repository.UserRepository;
import tn.fynova.spring.service.ICreditService;
import tn.fynova.spring.service.IProjectService;
import tn.fynova.spring.service.ITransactionService;
import tn.fynova.spring.service.TransactionExcelExporter;

@Controller(value = "projectController") // Name of the bean in Spring IoC
@ELBeanName(value = "projectController") // Name
public class ProjectController {

	private int nbreC;

	private int idprojet;
	private float projectcost;

	public float getProjectcost() {
		return projectcost;
	}

	public void setProjectcost(float projectcost) {
		this.projectcost = projectcost;
	}

	public int getNbreC() {
		return nbreC;
	}

	public void setNbreC(int nbreC) {
		this.nbreC = nbreC;
	}

	public int getIdprojet() {
		return idprojet;
	}

	public void setIdprojet(int idprojet) {
		this.idprojet = idprojet;
	}

	private float amount;

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	@Autowired
	IProjectService projetService;

	private List<Project> projects;

	public IProjectService getProjetService() {
		return projetService;
	}

	public void setProjetService(IProjectService projetService) {
		this.projetService = projetService;
	}

	public List<Project> getProjects() {
		projects = projetService.retrieveAllProjects();
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public void removeUser (int Id ) {
	projetService.deleteProject(Id);
	}

}
