package com.defensive.defensiveprogramming.factory;

import com.defensive.defensiveprogramming.model.Card;
import com.defensive.defensiveprogramming.model.request.CardRequest;

public abstract class CardFactory {
    protected abstract Card createCreditCard(CardRequest type);

    public Card getCreditCard(CardRequest type) {
        return createCreditCard(type);
    }
}
