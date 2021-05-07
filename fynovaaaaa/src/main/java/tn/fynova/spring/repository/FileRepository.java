package tn.fynova.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.fynova.spring.entities.DatabaseFile;


public interface FileRepository extends JpaRepository<DatabaseFile, String> {

}
