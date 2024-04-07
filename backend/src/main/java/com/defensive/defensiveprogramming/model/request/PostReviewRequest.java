package com.defensive.defensiveprogramming.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostReviewRequest(

        @NotBlank
        String review,
        @NotNull
        double rating

) {
}
