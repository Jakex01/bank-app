package com.defensive.defensiveprogramming.strategy;

import com.defensive.defensiveprogramming.mapstruct.DebitCardMapper;
import com.defensive.defensiveprogramming.model.Card;
import com.defensive.defensiveprogramming.model.CardDTO;
import com.defensive.defensiveprogramming.model.DebitCard;
import org.springframework.stereotype.Component;

@Component
public class DebitCardMappingStrategy  implements CardMappingStrategy<DebitCard>{
    @Override
    public boolean supports(Class<? extends Card> clazz) {
        return DebitCard.class.isAssignableFrom(clazz);
    }

    @Override
    public CardDTO map(DebitCard card) {
        return DebitCardMapper.INSTANCE.debitCardToCardDTO(card);
    }
}
