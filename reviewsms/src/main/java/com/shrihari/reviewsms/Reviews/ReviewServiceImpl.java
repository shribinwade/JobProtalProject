package com.shrihari.reviewsms.Reviews;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{
    @Autowired
    ReviewRepo reviewRepo;
    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> byCompanyId = reviewRepo.findByCompanyId(companyId);
        return byCompanyId;
    }
    @Override
    public boolean createReview(Long id,Review review) {
        if(id != null & review != null){
            review.setCompanyId(id);
            Review save = reviewRepo.save(review);
            if(save != null){
                return true;
            }
        }
        return false;
    }
    public Review getReview(Long reviewId) {
       return reviewRepo.findById(reviewId).orElse(null);
    }
    @Override
    public boolean updateReview(Long reviewId, Review updatedReview) {
        Review review = reviewRepo.findById(reviewId).orElse(null);
        if(review != null){
             review.setTitle(updatedReview.getTitle());
             review.setDescription(updatedReview.getDescription());
             review.setRating(updatedReview.getRating());
             review.setCompanyId(updatedReview.getCompanyId());
             reviewRepo.save(review);
             return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        Review review = reviewRepo.findById(reviewId).orElse(null);
        if(review != null){
            reviewRepo.delete(review);
            return true;
        }else{
            return false;
        }
    }
}
