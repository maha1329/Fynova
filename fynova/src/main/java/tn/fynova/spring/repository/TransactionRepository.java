package tn.fynova.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import tn.fynova.spring.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
