package tn.fynova.spring.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.fynova.spring.entities.Groupe;
import tn.fynova.spring.entities.User;
import tn.fynova.spring.repository.GroupRepository;

@Service
@Transactional
public class GroupServiceImpl implements IGroupService {
	@Autowired
	GroupRepository groupRepository;
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired
    public JavaMailSender emailSender;
	
	@Override
	public List<Groupe> showMeAllGroups(){
		List<Groupe> groups=groupRepository.findAll();
		return groups;
	}
	
	@Override
	public Groupe addGroup(Groupe g){
		//je teste si tout les Id existent dans la table user 
		for (User u : g.getUsers()) {
			if (userServiceImpl.retrieveUser(u.getUser_id()).getClass().equals(u.getClass())==false) {
				System.out.println("un id n'existe pas");
				return null;
			}
		}
		//si les id users sont tous là je crée le groupe
		Groupe groupe=groupRepository.save(g);
		return groupe;
		
	}
	
	@Override
	public void deleteGroup(int id) {
		groupRepository.deleteById(id);
	}
	
	//remplir chakkaka
	@Override
	public void chakakaInc(int i) {
		Optional<Groupe> g=groupRepository.findById(i);
		int c=0;
		for (User u : g.get().getUsers() ) {
			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
			System.out.println(u.toString());
			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
			//diminuer "amount" de leurs comptes et l'additionner à "chakaka"
			u.getUser_accounts().get(0).setAccount_balance((float) (u.getUser_accounts().get(0).getAccount_balance()-g.get().getAmount()));
			c++;
			System.out.println("boucle for sucess");
		}
		
		g.get().setChakaka(g.get().getChakaka()+(c*g.get().getAmount()));
		System.out.println(g.get().getChakaka());
		
	}
	
	//prendre de l'argent du "chakaka"
	@Override
	public void chakakaDec(int i) {
		boolean accord=true;
		User u=userServiceImpl.retrieveUser(i).get();
		for (User user : u.getGroupes().get(0).getUsers() ) {
			if (user.getAccordGroup()==0) {
				accord=false;
			}
			if(accord=false)
			break;
		}
		if (accord=true) {
		u.getGroupes().get(0).setChakaka(0);
		u.getUser_accounts().get(0).setAccount_balance((float) (u.getUser_accounts().get(0).getAccount_balance()+u.getGroupes().get(0).getChakaka()));
		}
	}
	
	//je veux entrer avec le groupe
	@Override
	public void addingUser(int idGrp,int idUser) {
		User u=userServiceImpl.retrieveUser(idUser).get();
		groupRepository.findById(idGrp).get().getUsers().add(u);
		
	}
	
	
	

}
