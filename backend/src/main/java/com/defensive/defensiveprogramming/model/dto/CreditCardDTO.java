package com.defensive.defensiveprogramming.model.dto;

import com.defensive.defensiveprogramming.model.CardDTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreditCardDTO implements CardDTO {

    private Long id;
    private String cardNumber;
    private LocalDateTime creditCardValidationDate;
    private String cvvCode;
    private boolean cardStatus;
    private String cardType;
    private double creditLimit;
    private double moneyTaken;
    private LocalDateTime nextPaymentDate;
}
