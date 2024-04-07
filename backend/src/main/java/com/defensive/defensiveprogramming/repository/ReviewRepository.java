package com.defensive.defensiveprogramming.repository;


import com.defensive.defensiveprogramming.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {


}
