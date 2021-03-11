package tn.fynova.spring.service;

import java.util.List;
import java.util.Optional;


import tn.fynova.spring.entities.Transaction;

public interface ITransactionService {

	Transaction addTransaction(Transaction t);

	void deleteTransaction(int id);

	

	
	List<Transaction> retrieveAllTransactions();

	Optional<Transaction> retrieveTransactions(int id);

	 

}