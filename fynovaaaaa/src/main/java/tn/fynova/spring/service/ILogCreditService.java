package tn.fynova.spring.service;

import java.util.List;

import tn.fynova.spring.entities.LogCredit;


public interface ILogCreditService {
	
	LogCredit addLogCredit(LogCredit L);

	void deleteLogCredit(int id);

	List<LogCredit> retrieveAllLogCredit();

	LogCredit retrieveLogCredit(int id);
}
