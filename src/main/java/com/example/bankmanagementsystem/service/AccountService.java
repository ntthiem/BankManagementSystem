package com.example.bankmanagementsystem.service;

import com.example.bankmanagementsystem.entity.Account;
import com.example.bankmanagementsystem.entity.Bank;
import com.example.bankmanagementsystem.entity.Customer;
import com.example.bankmanagementsystem.repository.AccountRepository;
import com.example.bankmanagementsystem.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccount() {
        return (List<Account>) accountRepository.findAll();
    }

    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    public Optional<Account> findAccountById(Long id) {
        return accountRepository.findById(id);
    }

    public List<Account> findAccountByNumber(String number) {
        return accountRepository.findByNumber(number);
    }

    public Account saveAndFlushAccount(Account account) {
        return (Account) accountRepository.saveAndFlush(account);
    }

    public List<Account> findAccountByBankId(Long bankId) {
        return accountRepository.findByBankId(bankId);
    }

    public List<Account> findAccountByCustomertId(Long bankId) {
        return accountRepository.findByCustomerId(bankId);
    }
}
