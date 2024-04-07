package com.defensive.defensiveprogramming.service;

import com.defensive.defensiveprogramming.exception.BankClientNotFoundException;
import com.defensive.defensiveprogramming.mapstruct.MapStructMapper;
import com.defensive.defensiveprogramming.model.BankClient;
import com.defensive.defensiveprogramming.model.Card;
import com.defensive.defensiveprogramming.model.Review;
import com.defensive.defensiveprogramming.model.request.PostReviewRequest;
import com.defensive.defensiveprogramming.repository.BankClientRepository;
import com.defensive.defensiveprogramming.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{


    private final BankClientRepository bankClientRepository;
    private final ReviewRepository reviewRepository;
    @Override
    public ResponseEntity<?> postReview(PostReviewRequest postReviewRequest, Authentication authentication) {


        BankClient principal = (BankClient) authentication.getPrincipal();

        BankClient bankClient = bankClientRepository.findById(principal.getId())
                .orElseThrow(() -> new BankClientNotFoundException("BankClient not found"));

        Review review = MapStructMapper.INSTANCE.review(postReviewRequest);

        review.setBankClient(bankClient);

        reviewRepository.save(review);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
