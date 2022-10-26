package com.example.bankmanagementsystem.controller;

import com.example.bankmanagementsystem.entity.Account;
import com.example.bankmanagementsystem.entity.Customer;
import com.example.bankmanagementsystem.service.AccountService;
import com.example.bankmanagementsystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private AccountService accountService;

    @RequestMapping("/customer")
    public String getAll(Model model) {
        List<Customer> customers = customerService.getAllCustomer();
        model.addAttribute("customers", customers);
        return "customer/customer_list";
    }

    @RequestMapping(value = "customer/add")
    public String add(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/customer_add";
    }

    @RequestMapping(value = "customer/save", method = RequestMethod.POST)
    public String save(Customer customer) {
        try {
            customerService.saveCustomer(customer);
        } catch (Exception e) {
        }
        return "redirect:/customer";
    }

    @RequestMapping(value = "customer/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") Long customerId, Model model) {
        Optional<Customer> customerEdit = customerService.findCustomerById(customerId);
        customerEdit.ifPresent(customer -> model.addAttribute("customer", customer));
        return "customer/customer_edit";
    }
    @RequestMapping(value = "/customer/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") Long customerId, Model model) {
        List<Account> account = accountService.findAccountByCustomertId(customerId);
        if(account.size() > 0) {
            model.addAttribute("errorMessage","This customer has an account, can't delete.");
            List<Customer> customers = customerService.getAllCustomer();
            model.addAttribute("customers", customers);
            return "customer/customer_list";
        }
        customerService.deleteCustomer(customerId);
        return "redirect:/customer";
    }
}
