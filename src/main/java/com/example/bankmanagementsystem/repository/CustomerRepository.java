package com.example.bankmanagementsystem.repository;

import com.example.bankmanagementsystem.entity.Bank;
import com.example.bankmanagementsystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
