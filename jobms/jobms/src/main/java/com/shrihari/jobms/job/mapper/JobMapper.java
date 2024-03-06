package com.shrihari.jobms.job.mapper;

import com.shrihari.jobms.job.Job;
import com.shrihari.jobms.job.dto.JobDTO;
import com.shrihari.jobms.job.external.Company;
import com.shrihari.jobms.job.external.Review;

import java.util.List;

public class JobMapper {

    public static JobDTO mapToJobWithDTO(Job job, Company company, List<Review> reviews){
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setCompany(company);
        jobDTO.setReviews(reviews);

        return  jobDTO;
    }
}
