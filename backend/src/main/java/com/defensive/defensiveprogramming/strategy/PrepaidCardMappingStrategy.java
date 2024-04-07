package com.defensive.defensiveprogramming.strategy;

import com.defensive.defensiveprogramming.mapstruct.PrepaidCardMapper;
import com.defensive.defensiveprogramming.model.Card;
import com.defensive.defensiveprogramming.model.CardDTO;
import com.defensive.defensiveprogramming.model.PrePaidCard;
import org.springframework.stereotype.Component;

@Component
public class PrepaidCardMappingStrategy implements CardMappingStrategy<PrePaidCard>{
    @Override
    public boolean supports(Class<? extends Card> clazz) {
        return false;
    }

    @Override
    public CardDTO map(PrePaidCard card) {
        return PrepaidCardMapper.INSTANCE.prepaidCardToCardDTO(card);
    }
}
