package com.defensive.defensiveprogramming.exception;

public class BankClientNotFoundException extends RuntimeException {

    public BankClientNotFoundException(String message) {
        super(message);
    }
}
