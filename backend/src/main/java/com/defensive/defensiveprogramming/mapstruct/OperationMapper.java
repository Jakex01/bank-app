package com.defensive.defensiveprogramming.mapstruct;

import com.defensive.defensiveprogramming.model.Operation;
import com.defensive.defensiveprogramming.model.PrePaidCard;
import com.defensive.defensiveprogramming.model.dto.OperationDTO;
import com.defensive.defensiveprogramming.model.dto.PrepaidCardDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OperationMapper {

    OperationMapper INSTANCE = Mappers.getMapper(OperationMapper.class);

    OperationDTO operationToOperationDTO(Operation operation);
}
