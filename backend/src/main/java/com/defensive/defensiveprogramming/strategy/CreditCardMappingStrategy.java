package com.defensive.defensiveprogramming.strategy;

import com.defensive.defensiveprogramming.mapstruct.CreditCardMapper;
import com.defensive.defensiveprogramming.model.Card;
import com.defensive.defensiveprogramming.model.CardDTO;
import com.defensive.defensiveprogramming.model.CreditCard;
import org.springframework.stereotype.Component;


@Component
public class CreditCardMappingStrategy implements CardMappingStrategy<CreditCard> {

    @Override
    public boolean supports(Class<? extends Card> clazz) {
        return CreditCard.class.isAssignableFrom(clazz);
    }

    @Override
    public CardDTO map(CreditCard card) {
        return CreditCardMapper.INSTANCE.creditCardToCardDTO(card);
    }
}
