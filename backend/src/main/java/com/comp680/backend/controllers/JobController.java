package com.comp680.backend.controllers;

import com.comp680.backend.repositories.JobRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import javax.validation.Valid;
import com.comp680.backend.models.Jobs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
 @CrossOrigin(origins = "http://localhost:4200",allowedHeaders="*", maxAge = 3600)
public class JobController {

@Autowired 
JobRepository jobRepo;

 @GetMapping("/all")
    public List<Jobs> findAll()
    {
        return jobRepo.findAll();
    }

 @PostMapping("/createJob")
    public Jobs create(@RequestBody Jobs job)
    {
    	System.out.println(job.description + " " + job.employment_type);
        return jobRepo.save(job);
    }

 @DeleteMapping("/jobs/{id}")
     public List<Jobs> delete(@PathVariable("job_id") Long job_id)
     {
         jobRepo.deleteById(job_id);
         return jobRepo.findAll();
     }

 }