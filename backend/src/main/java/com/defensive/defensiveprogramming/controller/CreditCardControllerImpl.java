package com.defensive.defensiveprogramming.controller;

import com.defensive.defensiveprogramming.model.Card;
import com.defensive.defensiveprogramming.model.CardDTO;
import com.defensive.defensiveprogramming.model.request.CardRequest;

import com.defensive.defensiveprogramming.service.CreditCardServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/credit-card")
public class CreditCardControllerImpl{

    private final CreditCardServiceImpl creditCardService;

    @PostMapping("/create")
    public ResponseEntity<?> createCreditCard(@RequestBody @Valid CardRequest cardRequest){
    return creditCardService.createCard(cardRequest);
    }
    @GetMapping("/all")
    public ResponseEntity<List<CardDTO>> getAllCreditCards(Authentication authentication){
        return creditCardService.getAllCreditCards(authentication);
    }

    @RequestMapping("/operation")
    public ResponseEntity<?> creditCardOperation() {
        return null;
    }
}
