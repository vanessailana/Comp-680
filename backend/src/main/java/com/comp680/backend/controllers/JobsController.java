package com.comp680.backend.controllers;

import com.comp680.backend.repositories.JobsRepository;
import com.comp680.backend.repositories.QuestionsRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import javax.validation.Valid;
import com.comp680.backend.models.Job;
import com.comp680.backend.models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
 @CrossOrigin(origins = "http://localhost:4200",allowedHeaders="*", maxAge = 3600)
public class JobsController {

@Autowired 
JobsRepository jobRepo;



 @GetMapping("/all")
    public List<Job> findAll()
    {
        return jobRepo.findAll();
    }

 @PostMapping("/createJob")
    public Job create(@RequestBody Job job)
    {
    	
        return jobRepo.save(job);
    }

 @DeleteMapping("/jobs/{id}")
     public List<Job> delete(@PathVariable("id") Long id)

     {
         jobRepo.deleteById(id);
         return jobRepo.findAll();
     }

 }