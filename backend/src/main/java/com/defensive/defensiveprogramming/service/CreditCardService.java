package com.defensive.defensiveprogramming.service;


import com.defensive.defensiveprogramming.model.Card;
import com.defensive.defensiveprogramming.model.CardDTO;
import com.defensive.defensiveprogramming.model.request.CardRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CreditCardService {
    ResponseEntity<?> createCard(CardRequest cardRequest);

    ResponseEntity<List<CardDTO>> getAllCreditCards(Authentication authentication);
}
