package tn.fynova.spring.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.fynova.spring.entities.Groupe;
import tn.fynova.spring.repository.GroupRepository;
import tn.fynova.spring.service.GroupServiceImpl;

@RestController
@RequestMapping("/group")
public class GroupRestController {
	
	@Autowired
	GroupServiceImpl groupservice;
	
	@Autowired
	GroupRepository grouprepository;	
	
	@GetMapping("/all")
	@ResponseBody
	public List<Groupe> getProject (){

	List<Groupe> list =  groupservice.showMeAllGroups();
	return list;
	}
	
	@PostMapping("/add")
	@ResponseBody
	public Groupe addgroup(@RequestBody Groupe g) {
		Groupe grp=groupservice.addGroup(g);
		return grp;
		}
	
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public String deleteGroup(@PathVariable("id")int id) {
		 groupservice.deleteGroup(id);	
		 return "done";
	}
	
	@PostMapping("/chakaka/{id}")
	@ResponseBody
	public String chakakaRemp(@PathVariable("id") int id) {
		groupservice.chakakaInc(id);
		return "done";
		}
	

}
