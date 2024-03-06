package com.shrihari.reviewsms.Reviews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping()
    public ResponseEntity<List<Review>> GetAllReviews(@RequestParam  Long id){
        return new ResponseEntity<>(reviewService.getAllReviews(id), HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<String> createReview(@RequestParam Long id ,@RequestBody Review review){
        boolean review1 = reviewService.createReview(id,review);
        if(review1) {
            return new ResponseEntity<>("Review Added Successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId){
       return new ResponseEntity<>(reviewService.getReview(reviewId),HttpStatus.OK);
    }
    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId,
                                               @RequestBody Review review)
    {
        boolean isReviewUpdated = reviewService.updateReview(reviewId, review);
        if(isReviewUpdated) {
            return new ResponseEntity<>("Review updated Successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review not updated", HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){
        boolean isReviewDeleted = reviewService.deleteReview(reviewId);
        if(isReviewDeleted){
            return new ResponseEntity<>("Review Deleted Successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review not Deleted", HttpStatus.NOT_FOUND);
        }

    }
}
