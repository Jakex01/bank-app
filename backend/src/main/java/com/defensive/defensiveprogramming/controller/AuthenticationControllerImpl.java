package com.defensive.defensiveprogramming.controller;

import com.defensive.defensiveprogramming.model.request.AuthenticationRequest;
import com.defensive.defensiveprogramming.model.request.VerificationRequest;
import com.defensive.defensiveprogramming.model.response.AuthenticationResponse;
import com.defensive.defensiveprogramming.model.request.RegisterRequest;
import com.defensive.defensiveprogramming.service.AuthenticationServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

//@CrossOrigin(origins = "http://localhost:4200/login")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationControllerImpl{

    private final AuthenticationServiceImpl service;

//    @CrossOrigin(origins = "http://localhost:4200/login")
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ) {
        var response = service.register(request);

        if(request.isMfaEnabled()){
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.accepted().build();
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }
    @PostMapping("/verify")
    public ResponseEntity<?> verifyCode(
            @RequestBody VerificationRequest verificationRequest
    ){
        return ResponseEntity.ok(service.verifyCode(verificationRequest));
    }

}
