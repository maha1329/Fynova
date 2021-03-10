package tn.fynova.spring.service;

import java.util.List;
import java.util.Optional;

import tn.fynova.spring.entities.Credit;

public interface ICreditService {
	Credit addCredit(Credit e);
	void deleteCredit(int id);
	List<Credit> retrieveAllCredits();
	Optional<Credit>  retrieveCredit(int id);
}
