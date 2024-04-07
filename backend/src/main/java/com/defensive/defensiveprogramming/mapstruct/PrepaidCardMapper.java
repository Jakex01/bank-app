package com.defensive.defensiveprogramming.mapstruct;

import com.defensive.defensiveprogramming.model.CreditCard;
import com.defensive.defensiveprogramming.model.PrePaidCard;
import com.defensive.defensiveprogramming.model.dto.CreditCardDTO;
import com.defensive.defensiveprogramming.model.dto.PrepaidCardDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PrepaidCardMapper {

    PrepaidCardMapper INSTANCE = Mappers.getMapper(PrepaidCardMapper.class);

    PrepaidCardDTO prepaidCardToCardDTO(PrePaidCard creditCard);
}
