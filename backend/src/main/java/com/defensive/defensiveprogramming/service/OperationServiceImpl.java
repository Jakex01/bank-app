package com.defensive.defensiveprogramming.service;

import com.defensive.defensiveprogramming.exception.BankClientNotFoundException;
import com.defensive.defensiveprogramming.mapstruct.OperationMapper;
import com.defensive.defensiveprogramming.model.BankClient;
import com.defensive.defensiveprogramming.model.Operation;
import com.defensive.defensiveprogramming.model.dto.OperationDTO;
import com.defensive.defensiveprogramming.repository.BankClientRepository;
import com.defensive.defensiveprogramming.repository.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService{

    private final BankClientRepository bankClientRepository;
    private final OperationRepository operationRepository;
    @Override
    public ResponseEntity<List<OperationDTO>> getAllOperationsByUser(Authentication authentication, int page, int size) {

        BankClient principal = (BankClient) authentication.getPrincipal();

        BankClient bankClient = bankClientRepository.findById(principal.getId())
                .orElseThrow(() -> new BankClientNotFoundException("BankClient not found"));

        Page<Operation> operationsPage = operationRepository
                .findAllByBankClient(bankClient, PageRequest.of(page, size));

        List<OperationDTO> operationDTOS = operationsPage.getContent()
                .stream()
                .map(OperationMapper.INSTANCE::operationToOperationDTO)
                .toList();

        return ResponseEntity.ok(operationDTOS);
    }
}
