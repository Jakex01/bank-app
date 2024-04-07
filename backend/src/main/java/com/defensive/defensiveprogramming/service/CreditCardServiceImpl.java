package com.defensive.defensiveprogramming.service;


import com.defensive.defensiveprogramming.exception.BankClientNotFoundException;
import com.defensive.defensiveprogramming.factory.CardFactory;
import com.defensive.defensiveprogramming.factory.UserCardFactory;
import com.defensive.defensiveprogramming.model.BankClient;
import com.defensive.defensiveprogramming.model.Card;
import com.defensive.defensiveprogramming.model.CardDTO;
import com.defensive.defensiveprogramming.model.request.CardRequest;
import com.defensive.defensiveprogramming.repository.BankClientRepository;
import com.defensive.defensiveprogramming.repository.CreditCardRepository;
import com.defensive.defensiveprogramming.strategy.CardMappingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService{

    private final BankClientRepository bankClientRepository;
    private final CreditCardRepository cardRepository;
    private final List<CardMappingStrategy<?>> strategies;


    @Override
    public ResponseEntity<?> createCard(CardRequest cardRequest) {

        BankClient bankClient1 = bankClientRepository
                .findBankClientByBankAccNumber(cardRequest.getBankAccNumber())
                .orElseThrow(() ->
                new NullPointerException("Bank client with account number " + cardRequest.getBankAccNumber() + " not found.")
        );
        CardFactory factory = new UserCardFactory();

        Card card = factory.getCreditCard(cardRequest);

        card.setBankClient(bankClient1);
        card.setCardNumber(cardRequest.getCardNumber());
        card.setCardStatus(true);
        card.setCardType(cardRequest.getCardType());
        card.setPin(cardRequest.getPin());
        card.setCreditCardValidationDate(cardRequest.getCreditCardValidationDate());
        card.setCvvCode(cardRequest.getCvvCode());
        card.setCardStatus(true);

        cardRepository.save(card);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<List<CardDTO>> getAllCreditCards(Authentication authentication) {

        BankClient principal = (BankClient) authentication.getPrincipal();


        BankClient bankClient = bankClientRepository.findById(principal.getId())
                .orElseThrow(() -> new BankClientNotFoundException("BankClient not found"));


        List<CardDTO> cardDTOs = cardRepository.findCreditCardsByBankClient(bankClient).stream()
                .map(card -> strategies.stream()
                        .filter(strategy -> strategy.supports(card.getClass()))
                        .findFirst()
                        .map(strategy -> {
                            // Tutaj wykonujemy bezpieczne rzutowanie karty na oczekiwany przez strategię typ.
                            // Musimy być pewni, że supports() gwarantuje, iż rzutowanie jest bezpieczne.
                            @SuppressWarnings("unchecked") // Tłumienie ostrzeżenia o niekontrolowanym rzutowaniu
                            CardMappingStrategy<Card> safeStrategy = (CardMappingStrategy<Card>) strategy;
                            return safeStrategy.map(card);
                        })
                        .orElseThrow(() -> new IllegalArgumentException("Unsupported card type")))
                .collect(Collectors.toList());

        return ResponseEntity.ok(cardDTOs);
    }
}