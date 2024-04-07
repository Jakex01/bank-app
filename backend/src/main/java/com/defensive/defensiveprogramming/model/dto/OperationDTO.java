package com.defensive.defensiveprogramming.model.dto;

import com.defensive.defensiveprogramming.model.OperationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class OperationDTO {

    private Long id;

    private OperationType operationType;
    private double amount;
    private LocalDateTime operationDate;
    private String operationDescription;
    private String operationReceiver;

}
