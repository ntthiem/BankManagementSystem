package com.example.bankmanagementsystem.controller;

import com.example.bankmanagementsystem.entity.Account;
import com.example.bankmanagementsystem.entity.Bank;
import com.example.bankmanagementsystem.entity.Customer;
import com.example.bankmanagementsystem.service.AccountService;
import com.example.bankmanagementsystem.service.BankService;
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
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private BankService bankService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/account")
    public String getAll(Model model) {
        List<Account> accounts = accountService.getAllAccount();
        model.addAttribute("accounts", accounts);
        return "account/account_list";
    }

    @RequestMapping(value = "account/add")
    public String add(Model model) {
        List<Bank> banks = bankService.getAllBank();
        model.addAttribute("banks", banks);
        List<Customer> customers = customerService.getAllCustomer();
        model.addAttribute("customers", customers);
        model.addAttribute("account", new Account());
        return "account/account_add";
    }

    @RequestMapping(value = "account/detail", method = RequestMethod.GET)
    public String show(@RequestParam("id") Long accountId, Model model) {
        Optional<Account> accountById = accountService.findAccountById(accountId);
        accountById.ifPresent(account -> model.addAttribute("account", account));
        return "account/account_detail";
    }

    @RequestMapping(value = "account/save", method = RequestMethod.POST)
    public String save(Account account) {
        try {
            account.setStatus("1");
            accountService.saveAccount(account);
        } catch (Exception e) {
        }
        return "redirect:/account";
    }

    @RequestMapping(value = "account/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") Long accountId, Model model) {
        List<Bank> banks = bankService.getAllBank();
        model.addAttribute("banks", banks);
        Optional<Account> accountEdit = accountService.findAccountById(accountId);
        accountEdit.ifPresent(account -> model.addAttribute("account", account));
        return "account/account_edit";
    }
    @RequestMapping(value = "/account/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") Long accountId, Model model) {
        try {
            accountService.deleteAccount(accountId);
        } catch(Exception e) {
        }
        return "redirect:/account";
    }
    @RequestMapping(value = "/account/search", method = RequestMethod.GET)
    public String search(@RequestParam("number") String number, Model model) {
       List<Account> accounts = accountService.findAccountByNumber(number);
        model.addAttribute("accounts", accounts);
        model.addAttribute("number", number);
        return "account/account_list";
    }

    @RequestMapping(value = "account/deposit", method = RequestMethod.POST)
    public String deposit(Long accountId, Long deposit, Model model) {
        try {
            Account account = accountService.findAccountById(accountId).get();
            Float balance = deposit + account.getBalance();
            account.setBalance(balance);
            Account new_account = accountService.saveAndFlushAccount(account);
            model.addAttribute("account", new_account);
        } catch (Exception e) {
        }
        return "account/account_detail";
    }

    @RequestMapping(value = "account/withdraw", method = RequestMethod.POST)
    public String withdraw(Long accountId, Long withdraw, Model model) {
        try {
            Account account = accountService.findAccountById(accountId).get();
            Float balance = account.getBalance() - withdraw;
            if(balance<0) {
                model.addAttribute("errorMessage","Withdraw failed, not enough balance");
                model.addAttribute("account", account);
            } else {
                account.setBalance(balance);
                Account new_account = accountService.saveAndFlushAccount(account);
                model.addAttribute("account", new_account);
            }
        } catch (Exception e) {
        }
        return "account/account_detail";
    }
}
