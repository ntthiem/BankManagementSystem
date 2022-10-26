package com.example.bankmanagementsystem.repository;

import com.example.bankmanagementsystem.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository  extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {
    @Query("SELECT a FROM Account a WHERE a.number = ?1")
    List<Account> findByNumber(String number);
    @Query("SELECT a FROM Account a WHERE a.bank_id = ?1")
    List<Account> findByBankId(Long bankId);

    @Query("SELECT a FROM Account a WHERE a.customer_id = ?1")
    List<Account> findByCustomerId(Long customerId);
}
