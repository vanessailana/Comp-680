package com.comp680.backend.controllers;

import com.example.comp680.backend.repo.JobRepo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.CrossOrigin;
import javax.validation.Valid;
import com.example.comp680.backend.models.Jobs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
public class JobController {

@Autowired 
JobRepo jobRepo

 @GetMapping("/jobs")
    public List<Jobs> findAll()
    {
        return jobRepo.findAll();
    }

 @PostMapping("/createJob")
    public ProductModel create(@RequestBody Jobs job)
    {
        return jobRepo.save(jobs);
    }

 @DeleteMapping("/jobs/{id}")
     public List<Jobs> delete(@PathVariable("job_id") Long job_id)
     {
         jobRepo.deleteById(product_id);
         return jobRepo.findAll();
     }

 }