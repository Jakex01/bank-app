package com.defensive.defensiveprogramming.repository;

import com.defensive.defensiveprogramming.model.BankClient;
import com.defensive.defensiveprogramming.model.Card;
import com.defensive.defensiveprogramming.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCardRepository extends JpaRepository<Card, Long> {

    List<Card> findCreditCardsByBankClient(BankClient bankClient);

    Card findCardByBankClientAndId(BankClient bankClient, Long id);
}
