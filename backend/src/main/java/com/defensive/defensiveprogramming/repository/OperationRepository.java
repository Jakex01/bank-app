package com.defensive.defensiveprogramming.repository;

import com.defensive.defensiveprogramming.model.BankClient;
import com.defensive.defensiveprogramming.model.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {

    List<Operation> findAllByBankClientBankAccNumber(BankClient bankAccNumber);

    Page<Operation> findAllByBankClient(BankClient bankClient, Pageable pageable);

    Operation findByBankClientAndId(BankClient bankClient, Long id);
}
