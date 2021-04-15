package tn.fynova.spring.service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.fynova.spring.entities.Account;
import tn.fynova.spring.entities.Claim;
import tn.fynova.spring.entities.Credit;
import tn.fynova.spring.repository.CreditRepository;
@Service
@Transactional

public class CreditServiceImpl implements ICreditService{
	@Autowired 
	CreditRepository creditRepository;
	@Autowired
	IAccountService accountService;
	@Override
	public Credit addCredit(Credit e) {
		Credit credit = creditRepository.save(e);
		return credit;
	}

	@Override
	public void deleteCredit(int id) {
		creditRepository.deleteById(id);
	}

	@Override
	public List<Credit> retrieveAllCredits() {
		List<Credit> credits = (List<Credit>) creditRepository.findAll();
		return credits;
	}

	@Override
	public Optional<Credit> retrieveCredit(int id) {
		Optional<Credit> credit = creditRepository.findById(id);
		return credit;
	}



}
