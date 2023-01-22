package com.example.bank.model;


import com.example.bank.exception.NotEnoughFundsException;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankAccount {
    private int id;
    private double balance;
    private String ownerFirstname;
    private String ownerLastname;


    public double deposit(double amount) {
        this.balance +=amount;
        return balance;
    }

    public double withdraw(double amount) {
        if (balance-amount >= 0) {
            this.balance-=amount;
            return balance;
        }
        throw new NotEnoughFundsException("Not enough funds to withdraw.");
    }
}
