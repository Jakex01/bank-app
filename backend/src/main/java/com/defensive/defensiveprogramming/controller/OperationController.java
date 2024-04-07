package com.defensive.defensiveprogramming.controller;

import com.defensive.defensiveprogramming.model.dto.OperationDTO;
import com.defensive.defensiveprogramming.service.OperationService;
import com.defensive.defensiveprogramming.service.OperationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/operation")
public class OperationController {

    private final OperationServiceImpl operationService;

    @GetMapping("/all")
    public ResponseEntity<List<OperationDTO>> getOperations(Authentication authentication,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "3") int size) {
        return operationService.getAllOperationsByUser(authentication, page, size);
    }
}
