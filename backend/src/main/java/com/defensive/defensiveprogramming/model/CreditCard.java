package com.defensive.defensiveprogramming.model;

import com.defensive.defensiveprogramming.validator.annotations.ValidCreditCard;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
//@ValidCreditCard
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditCard extends Card {

    private double creditLimit;
    private double moneyTaken;
    private LocalDateTime nextPaymentDate;

    @Override
    public double checkDebt() {
        return this.moneyTaken;
    }

    @Override
    public void withdrawMoney() {


    }

    @Override
    public void pay() {

    }
}
