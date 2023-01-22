package com.example.bank.service;

import com.example.bank.enums.TransactionStatus;
import com.example.bank.exception.AccountNotFoundException;
import com.example.bank.exception.NotEnoughFundsException;
import com.example.bank.model.BankAccount;
import com.example.bank.model.TransactionRequest;
import com.example.bank.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {
    private BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public void register(BankAccount bankAccount) {
        bankAccountRepository.save(bankAccount);
    }

    public TransactionRequest withdrawFromAccount(int id, double amount) {
        BankAccount account = findById(id);
        try {
            account.withdraw(amount);
            return new TransactionRequest(TransactionStatus.ACCEPTED, account.getBalance());
        } catch (NotEnoughFundsException e) {
            return new TransactionRequest(TransactionStatus.DECLINED, account.getBalance());
        } catch (AccountNotFoundException e) {
            return new TransactionRequest(TransactionStatus.DECLINED, 0.00);
        }
    }

    public TransactionRequest depositToAccount(int id, double amount) {
        BankAccount account = findById(id);
        account.deposit(amount);
        return new TransactionRequest(TransactionStatus.ACCEPTED, account.getBalance());
    }

    public BankAccount findById(int id) {
        Optional<BankAccount> checkedId = bankAccountRepository.findById(id);
        if (checkedId.isPresent()) {
            return checkedId.get();
        } else {
            throw new AccountNotFoundException("No such account");
        }
    }

    public List<BankAccount> getAllAccounts() {
        return bankAccountRepository.getAllAccounts();
    }
}
