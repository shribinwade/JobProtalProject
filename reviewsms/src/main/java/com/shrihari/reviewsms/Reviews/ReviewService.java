package com.shrihari.reviewsms.Reviews;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    boolean createReview(Long id,Review review);
    Review getReview(Long reviewId);
    boolean updateReview(Long reviewId,Review review);
    boolean deleteReview(Long reviewId);
}
