package com.example.bank.service;

import com.example.bank.enums.TransactionStatus;
import com.example.bank.exception.NotEnoughFundsException;
import com.example.bank.model.BankAccount;
import com.example.bank.model.TransactionRequest;
import com.example.bank.repository.BankAccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

class BankAccountServiceTest {
    private final BankAccountRepository bankAccountRepository = Mockito.mock(BankAccountRepository.class);
    private final BankAccountService bankAccountService = new BankAccountService(bankAccountRepository);
    private BankAccount bankAccount1, bankAccount2, bankAccount3;
    private List<BankAccount> accounts;

    @BeforeEach
        //given
    void setUp() {
        bankAccount1 = new BankAccount(1, 100.00, "John", "Doe");
        bankAccount2 = new BankAccount(2, 150.00, "Emmy", "LaFleur");
        bankAccount3 = new BankAccount(3, 160.00, "Hugh", "Grunt");
        accounts = List.of(bankAccount1, bankAccount2, bankAccount3);
    }

    @Test
    void shouldFindAccountById() {
        //given
        BankAccount ba;
        int id = 1;
        //when
        Mockito.when(bankAccountRepository.findById(1)).thenReturn(Optional.ofNullable(bankAccount1));
        ba = bankAccountService.findById(1);
        //then
        Assertions.assertEquals(bankAccount1, ba);
    }

    @Test
    void shouldReturnAllAccounts() {
        //when
        Mockito.when(bankAccountRepository.getAllAccounts()).thenReturn(accounts);
        List<BankAccount> query = bankAccountService.getAllAccounts();

        //then
        Assertions.assertEquals(query, accounts);
    }

    @Test
    void shouldWithdrawFromAccount() {
        //given
        int id = 1;
        double amount = 50;
        TransactionRequest transactionRequest;

        //when
        Mockito.when(bankAccountRepository.findById(id)).thenReturn(Optional.ofNullable(bankAccount1));
        transactionRequest = bankAccountService.withdrawFromAccount(id, amount);

        //then
        Assertions.assertEquals(TransactionStatus.ACCEPTED, transactionRequest.getTransactionStatus());
        Assertions.assertEquals(50.00, transactionRequest.getNewBalance());
    }

    @Test
    void shouldNotAllowWithdrawalOverBalance() {
        //given
        int id = 1;
        double amount = 150;
        TransactionRequest transactionRequest;

        //when
        Mockito.when(bankAccountRepository.findById(id)).thenReturn(Optional.ofNullable(bankAccount1));
        transactionRequest = bankAccountService.withdrawFromAccount(id, amount);

        //then
        Assertions.assertEquals(TransactionStatus.DECLINED, transactionRequest.getTransactionStatus());

    }

    @Test
    void shouldThrowExceptionWhenAttemptToGoOverBalance() {
        //given
        int id = 1;
        double amount = 150;
        TransactionRequest transactionRequest;

        //when
        Mockito.when(bankAccountRepository.findById(id)).thenReturn(Optional.ofNullable(bankAccount1));
        transactionRequest = bankAccountService.withdrawFromAccount(id, amount);

        //then
        Assertions.assertThrows(NotEnoughFundsException.class, () -> {
            bankAccountService.withdrawFromAccount(id, amount);
        });
    }

}