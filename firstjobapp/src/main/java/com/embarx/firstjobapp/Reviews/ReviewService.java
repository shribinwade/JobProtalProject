package com.embarx.firstjobapp.Reviews;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews(Long companyId);
    boolean createReview(Long id,Review review);
    Review getReview(Long id, Long reviewId);

    boolean updateReview(Long id,Long reviewId,Review review);

    boolean deleteReview(Long id, Long reviewId);
}
