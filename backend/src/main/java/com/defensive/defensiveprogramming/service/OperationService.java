package com.defensive.defensiveprogramming.service;

import com.defensive.defensiveprogramming.model.dto.OperationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface OperationService {
    ResponseEntity<List<OperationDTO>> getAllOperationsByUser(Authentication authentication, int page, int size);
}
