package tn.fynova.spring.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;


import tn.fynova.spring.entities.Transaction;

public interface ITransactionService {

	Transaction addTransaction(Transaction t);

	void deleteTransaction(int id);

	List<Transaction> retrieveAllTransactions();

	Transaction retrieveTransactions(int id);

	List<Transaction> listAll();
	
	List<Transaction> listTransactionByUserId(int id) ;

    List<Transaction> listTransactionByDate(int annee);

	Map<Double, Double> StatisticMonthbyAmount(int year);
	
	float SumTransAmount(); 
}
