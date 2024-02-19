package com.embarx.firstjobapp.Reviews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{id}")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> GetAllReviews(@PathVariable Long id){
        return new ResponseEntity<>(reviewService.getAllReviews(id), HttpStatus.OK);
    }
    @PostMapping("/reviews")
    public ResponseEntity<String> createReview(@PathVariable Long id ,@RequestBody Review review){
        boolean review1 = reviewService.createReview(id,review);
        if(review1) {
            return new ResponseEntity<>("Review Added Successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long id,
                                            @PathVariable Long reviewId){
       return new ResponseEntity<>(reviewService.getReview(id,reviewId),HttpStatus.OK);

    }
    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long id,
                                               @PathVariable Long reviewId,
                                               @RequestBody Review review)
    {
        boolean isReviewUpdated = reviewService.updateReview(id, reviewId, review);
        if(isReviewUpdated) {
            return new ResponseEntity<>("Review updated Successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review not updated", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id,
                                               @PathVariable Long reviewId){
        boolean isReviewDeleted = reviewService.deleteReview(id,reviewId);
        if(isReviewDeleted){
            return new ResponseEntity<>("Review Deleted Successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review not Deleted", HttpStatus.NOT_FOUND);
        }

    }
}
