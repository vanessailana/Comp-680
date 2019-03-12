package com.comp680.backend.controllers;

import com.comp680.backend.repositories.JobsRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import javax.validation.Valid;
import com.comp680.backend.models.Job;
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
    	System.out.println(job.description + " " + job.employment_type);
        return jobRepo.save(job);
    }

 @DeleteMapping("/jobs/{id}")
<<<<<<< HEAD:backend/src/main/java/com/comp680/backend/controllers/JobsController.java
     public List<Job> delete(@PathVariable("id") Long id)
=======
     public List<Jobs> delete(@PathVariable("id") Long  job_id)
>>>>>>> CRUD_Job_Posting_Backend:backend/src/main/java/com/comp680/backend/controllers/JobController.java
     {
         jobRepo.deleteById(id);
         return jobRepo.findAll();
     }

 }