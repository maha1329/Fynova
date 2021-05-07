package tn.fynova.spring.service;

import java.util.List;

import tn.fynova.spring.entities.Demand;

public interface IDemandService {
	Demand adddemand(Demand L);

	void deletedemand(int id);

	List<Demand> retrieveAlldemand();

	Demand retrievedemand(int id);
}
