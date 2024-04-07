package com.defensive.defensiveprogramming.service;

import org.springframework.security.core.Authentication;

import java.io.ByteArrayInputStream;

public interface PdfService {
    ByteArrayInputStream generatePdfReport(Authentication authentication, Long id);
}
