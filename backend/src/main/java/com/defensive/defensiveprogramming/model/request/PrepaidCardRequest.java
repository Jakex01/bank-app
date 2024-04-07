package com.defensive.defensiveprogramming.model.request;

import lombok.Getter;

@Getter
public class PrepaidCardRequest extends CardRequest{
    private double dailyLimit;
    private double cashAvailable;
}
