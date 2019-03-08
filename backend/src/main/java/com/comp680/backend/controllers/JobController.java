package com.comp680.backend.controllers;

import com.comp680.backend.repo.JobRepo;
import org.springframework.web.bind.annotation.CrossOrigin;
import javax.validation.Valid;
import com.comp680.backend.models.Jobs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
public class JobController {

@Autowired 
JobRepo jobRepo;

 @GetMapping("/jobs")
    public List<Jobs> findAll()
    {
        return jobRepo.findAll();
    }

 @PostMapping("/createJob")
    public Jobs create(@RequestBody Jobs job)
    {
        return jobRepo.save(job);
    }

 @DeleteMapping("/jobs/{id}")
     public List<Jobs> delete(@PathVariable("job_id") Long job_id)
     {
         jobRepo.deleteById(job_id);
         return jobRepo.findAll();
     }

 }