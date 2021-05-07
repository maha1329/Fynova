package tn.fynova.spring.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.fynova.spring.entities.Demand;
import tn.fynova.spring.repository.DemandRepository;

@Service
public class DemandService implements IDemandService{

	@Autowired 
	DemandRepository demandRepository;
	
	@Override
	public Demand adddemand(Demand e) {
		Demand demand = demandRepository.save(e);
		return demand;
	}

	@Override
	public void deletedemand(int id) {
		demandRepository.deleteById(id);
	}

	@Override
	public List<Demand> retrieveAlldemand() {
		List<Demand> demands = (List<Demand>) demandRepository.findAll();
		return demands;
	}

	@Override
	public Demand retrievedemand(int id) {
		Optional<Demand> demandOptional = demandRepository.findById(id);
		Demand c = demandOptional.get();
		return c;
	}

}
