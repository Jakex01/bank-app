package com.defensive.defensiveprogramming.model.dto;

import com.defensive.defensiveprogramming.model.CardDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DebitCardDTO implements CardDTO {

    private Long id;
    private String cardNumber;
    private LocalDateTime creditCardValidationDate;
    private String cardType;
    private String cvvCode;
    private boolean cardStatus;
    private int pin;
    private double dailyWithdrawalLimit;
    private double transactionLimit;
    private double cashAvailable;
}
