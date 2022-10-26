package com.example.bankmanagementsystem.service;

import com.example.bankmanagementsystem.entity.Bank;
import com.example.bankmanagementsystem.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankService {
    @Autowired
    private BankRepository bankRepository;

    public List<Bank> getAllBank() {
        return (List<Bank>) bankRepository.findAll();
    }

    public void saveBank(Bank bank) {
        bankRepository.save(bank);
    }

    public void deleteBank(Long id) {
        bankRepository.deleteById(id);
    }

    public Optional<Bank> findBankById(Long id) {
        return bankRepository.findById(id);
    }
}
