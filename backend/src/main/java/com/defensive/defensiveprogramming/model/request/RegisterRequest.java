package com.defensive.defensiveprogramming.model.request;

import com.defensive.defensiveprogramming.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String personalIdentityNumber;
    private String bankAccNumber;
    private Role role;
    private boolean mfaEnabled;
}