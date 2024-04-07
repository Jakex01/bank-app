package com.defensive.defensiveprogramming.model;

import com.defensive.defensiveprogramming.validator.annotations.ValidCreditCard;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
//@ValidCreditCard
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrePaidCard extends Card{


    private double dailyLimit;
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
