package com.defensive.defensiveprogramming.factory;

import com.defensive.defensiveprogramming.model.Card;
import com.defensive.defensiveprogramming.model.CreditCard;
import com.defensive.defensiveprogramming.model.DebitCard;
import com.defensive.defensiveprogramming.model.PrePaidCard;
import com.defensive.defensiveprogramming.model.request.CardRequest;
import com.defensive.defensiveprogramming.model.request.CreditCardRequest;
import com.defensive.defensiveprogramming.model.request.DebitCardRequest;
import com.defensive.defensiveprogramming.model.request.PrepaidCardRequest;

import java.time.LocalDateTime;

public class UserCardFactory extends CardFactory{
    @Override
    protected Card createCreditCard(CardRequest cardRequest) {
        if (cardRequest instanceof CreditCardRequest creditRequest){
            LocalDateTime now = LocalDateTime.now();

            return CreditCard.builder()
                    .creditLimit(creditRequest.getCreditLimit())
                    .moneyTaken(0)
                    .nextPaymentDate(now.plusMonths(1))
                    .build();

        } else if (cardRequest instanceof DebitCardRequest debitCardRequest) {
            return DebitCard.builder()
                    .cashAvailable(debitCardRequest.getCashAvailable())
                    .dailyWithdrawalLimit(debitCardRequest.getDailyWithdrawalLimit())
                    .transactionLimit(debitCardRequest.getTransactionLimit())
                    .build();
        } else if (cardRequest instanceof PrepaidCardRequest prepaidCardRequest) {
            return PrePaidCard.builder()
                    .cashAvailable(prepaidCardRequest.getCashAvailable())
                    .dailyLimit(prepaidCardRequest.getDailyLimit())
                    .build();
        } else {
            throw new IllegalArgumentException("Unsupported card request type");
        }
    }
}
