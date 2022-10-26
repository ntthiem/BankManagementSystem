package com.example.bankmanagementsystem.service;

import com.example.bankmanagementsystem.entity.Bank;
import com.example.bankmanagementsystem.entity.Customer;
import com.example.bankmanagementsystem.repository.BankRepository;
import com.example.bankmanagementsystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomer() {
        return (List<Customer>) customerRepository.findAll();
    }
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public Optional<Customer> findCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer saveAndFlushCustomer(Customer customer) {
        return (Customer) customerRepository.saveAndFlush(customer);
    }
}
