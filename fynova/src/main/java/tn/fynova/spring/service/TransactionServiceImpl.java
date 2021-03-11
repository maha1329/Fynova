package tn.fynova.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import tn.fynova.spring.entities.Transaction;
import tn.fynova.spring.repository.TransactionRepository;

public class TransactionServiceImpl implements ITransactionService{
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Override
	public Transaction addTransaction(Transaction t) {
		Transaction transaction = transactionRepository.save(t);
		return transaction;
	}

	@Override
	public void deleteTransaction(int id) {
		transactionRepository.deleteById(id);		
	}

	@Override
	public List<Transaction> retrieveAllTransactions() {
		List<Transaction> transaction = (List<Transaction>)transactionRepository.findAll();
		return transaction;
	}

	@Override
	public Optional<Transaction> retrieveTransactions(int id) {
		Optional<Transaction>  transaction = transactionRepository.findById(id);
		return transaction;
	}

}
