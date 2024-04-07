package com.defensive.defensiveprogramming.model.request;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreditCardRequest extends CardRequest {
    private double creditLimit;
    private double moneyTaken;
    private LocalDateTime nextPaymentDate;
}
