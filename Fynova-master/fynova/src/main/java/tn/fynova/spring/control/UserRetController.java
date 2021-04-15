package tn.fynova.spring.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.fynova.spring.entities.User;
import tn.fynova.spring.service.UserServiceImpl;

@RestController
public class UserRetController {
	
	@Autowired 
	UserServiceImpl userSer ;
	
	@PostMapping("/addUser")
	@ResponseBody
	public void addUser (User u) {
		
		userSer.addUser(u);
	}
	
	

}
