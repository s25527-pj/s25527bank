package com.example.bank.repository;

import com.example.bank.model.BankAccount;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BankAccountRepository {
    private final List<BankAccount> accounts = new ArrayList<>();


    public void save(BankAccount bankAccount) {
        accounts.add(bankAccount);
    }

    public List<BankAccount> getAllAccounts() {
        return accounts;
    }

    public Optional<BankAccount> findById(int id) {
        return accounts.stream()
                .filter(ba -> ba.getId() == id)
                .findFirst();
    }


}
