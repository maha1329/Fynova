package tn.fynova.spring.service;

import java.util.List;
import java.util.Optional;

import tn.fynova.spring.entities.Account;
import tn.fynova.spring.entities.Credit;

public interface ICreditService {
	Credit addCredit(Credit e);

	void deleteCredit(int id);

	List<Credit> retrieveAllCredits();

	Credit retrieveCredit(int id);

	float val_mens(float amount, double interest, float nbre);

	List<String> InterestCalcul(int nbre, double Interest, float val_mens, float amount);

	List<String> contrats(float amount, double interest);

	String extractToPDF(String name);

	void PaymentDevis(int id,float price);
	
	public Credit  findCreditWithProject(int idprojet);

}
