package com.shrihari.jobms.job;
import com.shrihari.jobms.job.dto.JobDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {


    @Autowired
    private JobService jobService;

    @GetMapping()
    public List<JobDTO> findAll(){
        return jobService.findAll();
    }

    @PostMapping()
    public String createJob(@RequestBody Job job){
        jobService.createJob(job);
        return "Job added successfully";
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long id){
        JobDTO jobById = jobService.getJobById(id);
        if(jobById != null){
           return new ResponseEntity<>(jobById, HttpStatus.OK);
        }

        return new ResponseEntity<>(jobById, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean deleted = jobService.deleteJobById(id);
        if(deleted)
            return new ResponseEntity<>("Job deleted successfully",HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id,@RequestBody Job updateJob){
        boolean updated = jobService.updateJob(id,updateJob);
         if(updated){
             return new ResponseEntity<>("Job updated successfully",HttpStatus.OK);
         }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
