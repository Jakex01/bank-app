package com.defensive.defensiveprogramming.service;


import com.defensive.defensiveprogramming.model.request.AuthenticationRequest;
import com.defensive.defensiveprogramming.model.request.RegisterRequest;
import com.defensive.defensiveprogramming.model.response.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
