package com.defensive.defensiveprogramming.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private OperationType operationType;
    private double amount;
    private LocalDateTime operationDate;
    private String operationDescription;
    private String operationReceiver;



    @ManyToOne
    BankClient bankClient;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

}
