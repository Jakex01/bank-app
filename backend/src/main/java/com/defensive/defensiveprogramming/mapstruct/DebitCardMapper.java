package com.defensive.defensiveprogramming.mapstruct;

import com.defensive.defensiveprogramming.model.CreditCard;
import com.defensive.defensiveprogramming.model.DebitCard;
import com.defensive.defensiveprogramming.model.dto.CreditCardDTO;
import com.defensive.defensiveprogramming.model.dto.DebitCardDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DebitCardMapper {

    DebitCardMapper INSTANCE = Mappers.getMapper(DebitCardMapper.class);

    @Mapping(source = "cardType", target = "cardType")
    DebitCardDTO debitCardToCardDTO(DebitCard creditCard);
}
