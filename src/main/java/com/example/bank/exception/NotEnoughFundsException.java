package com.example.bank.exception;

public class NotEnoughFundsException extends RuntimeException {
    public NotEnoughFundsException(String message) {
        super(message);
    }
}
