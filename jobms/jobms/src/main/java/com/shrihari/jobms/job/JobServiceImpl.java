package com.shrihari.jobms.job;


import com.shrihari.jobms.job.dto.JobDTO;
import com.shrihari.jobms.job.external.Company;
import com.shrihari.jobms.job.external.Review;
import com.shrihari.jobms.job.mapper.JobMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService{


    @Autowired
    private JobRepo jobRepo;

    @Autowired
    RestTemplate restTemplate;


    @Override
    public List<JobDTO> findAll() {
        List<Job> jobs = jobRepo.findAll();
        List<JobDTO> jobDTOS = new ArrayList<>();
        //RestTemplate restTemplate = new RestTemplate();
        return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private JobDTO convertToDto(Job job){

//            JobDTO jobDTO = new JobDTO();
            
//            jobDTO.setJob(job);

        Company company = restTemplate.getForObject(
                    "http://COMPANY-APP-MS:8083/companies/"+job.getCompanyId(),
                    Company.class);

        ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange(
                "http://REVIEW-APP-MS:8084/reviews?id=" + job.getCompanyId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Review>>() {
                });
        List<Review> reviews = reviewResponse.getBody();
        JobDTO jobDTO = JobMapper.mapToJobWithDTO(job, company,reviews);

//        jobDTO.setCompany(company);
//        Review review = restTemplate.getForObject(
//                "http://COMPANY-APP-MS:8083/companies/"+job.getCompanyId(),
//                Company.class);
//        jobDTO.setCompany(company);
            return jobDTO;
    }

    @Override
    public void createJob(Job job) {
        jobRepo.save(job);
    }

    @Override
    public JobDTO getJobById(Long id) {

        Job job = jobRepo.findById(id).orElse(null);
        JobDTO jobDTO = convertToDto(job);
        return jobDTO;
//        if(byId.isPresent()){
//            return byId.get();
//        }
//        return null;
//        for (Job job:jobs) {
//           if(job.getId().equals(id)){
//                return job;
//           }
//        }
//        return null;
 }

    @Override
    public boolean deleteJobById(Long id) {

        Optional<Job> byId = jobRepo.findById(id);
        if(byId.isPresent()){
        jobRepo.deleteById(id);
        return true;
        }
        else{
            return false;
        }
        
//        Iterator<Job> iterator = jobs.iterator();
//
//        while (iterator.hasNext()){
//            Job job = iterator.next();
//            if(job.getId().equals(id)){
//                iterator.remove();
//                return true;
//            }
//        }
//        return false;
    }

    @Override
    public boolean updateJob(Long id, Job updateJob) {


        Optional<Job> byId = jobRepo.findById(id);
        if (byId.isPresent()) {
            Job job = byId.get();
            BeanUtils.copyProperties(updateJob, job);
            job.setId(id);
            Job save = jobRepo.save(job);

            if (save != null) {
                return true;
            }
        }
        return false;

//        for (Job job:jobs) {
//            if(job.getId().equals(id)){
//                BeanUtils.copyProperties(updateJob,job);
//                job.setId(id);
//                return true;
//            }
//        }
//
//        return false;
    }


}
