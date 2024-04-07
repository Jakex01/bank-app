package com.defensive.defensiveprogramming.mapstruct;

import com.defensive.defensiveprogramming.model.CreditCard;
import com.defensive.defensiveprogramming.model.dto.CreditCardDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreditCardMapper {

    CreditCardMapper INSTANCE = Mappers.getMapper(CreditCardMapper.class);
    @Mapping(source = "cardNumber", target = "cardNumber")
    @Mapping(source = "cardType", target = "cardType")
    CreditCardDTO creditCardToCardDTO(CreditCard creditCard);

}
