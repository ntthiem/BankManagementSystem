package com.example.bankmanagementsystem.controller;

import com.example.bankmanagementsystem.entity.Account;
import com.example.bankmanagementsystem.entity.Bank;
import com.example.bankmanagementsystem.service.AccountService;
import com.example.bankmanagementsystem.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class BankController {
    @Autowired
    private BankService bankService;

    @Autowired
    private AccountService accountService;
    @RequestMapping("/bank")
    public String getAll(Model model) {
        List<Bank> banks = bankService.getAllBank();
        model.addAttribute("banks", banks);
        return "bank/bank_list";
    }

    @RequestMapping(value = "bank/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") Long bankId, Model model) {
        Optional<Bank> bankEdit = bankService.findBankById(bankId);
        bankEdit.ifPresent(bank -> model.addAttribute("bank", bank));
        return "bank/bank_edit";
    }

    @RequestMapping(value = "bank/add")
    public String add(Model model) {
        model.addAttribute("bank", new Bank());
        return "bank/bank_add";
    }

    @RequestMapping(value = "bank/save", method = RequestMethod.POST)
    public String save(Bank bank) {
        try {
            bankService.saveBank(bank);
        } catch (Exception e) {
        }
        return "redirect:/bank";
    }

    @RequestMapping(value = "/bank/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") Long bankId, Model model) {
        List<Account> account = accountService.findAccountByBankId(bankId);
        if(account.size() > 0) {
            model.addAttribute("errorMessage","Can't delete this bank");
            List<Bank> banks = bankService.getAllBank();
            model.addAttribute("banks", banks);
            return "bank/bank_list";
        }
        bankService.deleteBank(bankId);
        return "redirect:/bank";
    }
}
