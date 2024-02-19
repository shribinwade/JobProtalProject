package com.embarx.firstjobapp.Reviews;

import com.embarx.firstjobapp.companies.Company;
import com.embarx.firstjobapp.companies.CompanyRepo;
import com.embarx.firstjobapp.companies.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{
    @Autowired
    ReviewRepo reviewRepo;

    @Autowired
    CompanyService companyService;

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> byCompanyId = reviewRepo.findByCompanyId(companyId);
        return byCompanyId;
    }

    @Override
    public boolean createReview(Long id,Review review) {
        Company company = companyService.getCompaniesById(id);
        if(company != null){
            review.setCompany(company);
            Review save = reviewRepo.save(review);
            if(save != null){
                return true;
            }
        }
        return false;
    }

    @Override
    public Review getReview(Long id, Long reviewId) {
        List<Review> reviews = reviewRepo.findByCompanyId(id);

        return reviews.stream()
                      .filter(review -> review.getId().equals(reviewId))
                      .findFirst()
                      .orElse(null);
    }

    @Override
    public boolean updateReview(Long id, Long reviewId, Review updatedReview) {
        if(companyService.getCompaniesById(id) != null){
             updatedReview.setCompany(companyService.getCompaniesById(id));
             updatedReview.setId(reviewId);
             reviewRepo.save(updatedReview);
             return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteReview(Long id, Long reviewId) {
        if(companyService.getCompaniesById(id) != null && reviewRepo.existsById(reviewId)){
            Review review = reviewRepo.findById(reviewId).orElse(null);
            Company company = review.getCompany();
            company.getReviews().remove(review);
            review.setCompany(null);
            companyService.updateCompany(id,company);
            reviewRepo.deleteById(reviewId);
            return true;
        }else{
            return false;
        }
    }
}
