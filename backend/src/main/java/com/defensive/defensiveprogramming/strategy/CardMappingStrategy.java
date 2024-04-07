package com.defensive.defensiveprogramming.strategy;

import com.defensive.defensiveprogramming.model.Card;
import com.defensive.defensiveprogramming.model.CardDTO;


public interface CardMappingStrategy<T extends Card> {
    boolean supports(Class<? extends Card> clazz);
    CardDTO map(T card);
}
