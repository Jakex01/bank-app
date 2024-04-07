package com.defensive.defensiveprogramming.model;

import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
//@ValidCreditCard
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DebitCard extends Card{

    private double dailyWithdrawalLimit;
    private double transactionLimit;
    private double cashAvailable;
    @Override
    public double checkDebt() {
        return 0;
    }

    @Override
    public void withdrawMoney() {

    }

    @Override
    public void pay() {

    }
}
