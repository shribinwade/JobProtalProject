package com.embarx.firstjobapp.job;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService{


    @Autowired
    private JobRepo jobRepo;

    @Override
    public List<Job> findAll() {

        List<Job> jobs = jobRepo.findAll();
        return jobs;
    }

    @Override
    public void createJob(Job job) {
        jobRepo.save(job);
    }

    @Override
    public Job getJobById(Long id) {

        Optional<Job> byId = jobRepo.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }
        return null;

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
