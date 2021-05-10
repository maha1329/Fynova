package tn.fynova.spring.service;

import java.util.List;

import tn.fynova.spring.entities.Groupe;
import tn.fynova.spring.entities.User;

public interface IGroupService {
	
	Groupe addGroup(Groupe g);
	List<Groupe> showMeAllGroups();
	void deleteGroup(int id);
	void chakakaInc(int i);
	void chakakaDec(int i); 
	void addingUser(int idGrp,int idUser);

}
