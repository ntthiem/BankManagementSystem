package com.example.bankmanagementsystem.repository;

import com.example.bankmanagementsystem.entity.Bank;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends CrudRepository<Bank, Long>, JpaSpecificationExecutor<Bank> {}
