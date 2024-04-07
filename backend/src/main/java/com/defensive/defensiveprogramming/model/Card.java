package com.defensive.defensiveprogramming.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public abstract class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private CardType cardType;
    private String pin;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private BankClient bankClient;
    private String cardNumber;
    private LocalDateTime creditCardValidationDate;
    private String cvvCode;
    private boolean cardStatus;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Operation> operations = new ArrayList<>();
    abstract double checkDebt();
    abstract void withdrawMoney();
    abstract void pay();


}
