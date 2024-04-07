package com.defensive.defensiveprogramming.model.request;


import lombok.Getter;

@Getter
public class DebitCardRequest extends CardRequest{

    private double dailyWithdrawalLimit;
    private double transactionLimit;
    private double cashAvailable;
}
