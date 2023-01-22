package com.example.bank.model;

import com.example.bank.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
    private TransactionStatus transactionStatus;
    private double newBalance;
}
