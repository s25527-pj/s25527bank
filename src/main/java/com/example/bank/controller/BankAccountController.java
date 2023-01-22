package com.example.bank.controller;

import com.example.bank.exception.AccountNotFoundException;
import com.example.bank.model.BankAccount;
import com.example.bank.model.TransactionRequest;
import com.example.bank.service.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankAccountController {

    private final BankAccountService bankAccountService;


    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/accounts")
    public List<BankAccount> getAll() {
        return bankAccountService.getAllAccounts();
    }

    @GetMapping("/accounts/{id}")
    public BankAccount getAccount(@PathVariable int id) {
        return bankAccountService.findById(id);
    }

    @PostMapping("/save")
    public void registerAccount(@RequestBody BankAccount bankAccount) {
        bankAccountService.register(bankAccount);
    }

    @PutMapping("/accounts/withdraw/{id}")
    public TransactionRequest withdrawFromAccount(@PathVariable int id, @RequestBody double amount) {
            return bankAccountService.withdrawFromAccount(id, amount);
    }

    @PutMapping("/accounts/deposit/{id}")
    public TransactionRequest depositToAccount(@PathVariable int id, @RequestBody double amount) {
        return bankAccountService.depositToAccount(id, amount);
    }
}
