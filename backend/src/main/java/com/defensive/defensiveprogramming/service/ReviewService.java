package com.defensive.defensiveprogramming.service;

import com.defensive.defensiveprogramming.model.request.PostReviewRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface ReviewService {


    ResponseEntity<?> postReview(PostReviewRequest postReviewRequest, Authentication authentication);

}
