package tn.fynova.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.fynova.spring.entities.LogCredit;
import tn.fynova.spring.repository.LogCreditRepository;

@Service
public class LogCreditServiceImpl implements ILogCreditService{

	@Autowired 
	LogCreditRepository LogCreditRepository;
	
	@Override
	public LogCredit addLogCredit(LogCredit e) {
		LogCredit LogCredit = LogCreditRepository.save(e);
		return LogCredit;
	}

	@Override
	public void deleteLogCredit(int id) {
		LogCreditRepository.deleteById(id);
	}

	@Override
	public List<LogCredit> retrieveAllLogCredit() {
		List<LogCredit> LogCredits = (List<LogCredit>) LogCreditRepository.findAll();
		return LogCredits;
	}

	@Override
	public LogCredit retrieveLogCredit(int id) {
		Optional<LogCredit> LogCreditOptional = LogCreditRepository.findById(id);
		LogCredit c = LogCreditOptional.get();
		return c;
	}

    
	
}
