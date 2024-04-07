package com.defensive.defensiveprogramming.model.request;

import com.defensive.defensiveprogramming.model.CardType;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "cardType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CreditCardRequest.class, name = "CREDIT"),
        @JsonSubTypes.Type(value = DebitCardRequest.class, name = "DEBIT"),
        @JsonSubTypes.Type(value = PrepaidCardRequest.class, name = "PREPAID"),
})
public abstract class CardRequest{
        String bankAccNumber;
        String pin;
        String cardNumber;
        CardType cardType;
        LocalDateTime creditCardValidationDate;
        String cvvCode;
        boolean cardStatus;
}
