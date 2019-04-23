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
//https://mysterious-harbor-56923.herokuapp.com
@RestController
@CrossOrigin(origins = "http://localhost:4200" , maxAge = 3600 )
public class JobsController {

@Autowired 
JobsRepository jobRepo;

    @GetMapping("jobs/example")
    public String example()
    {
        return "Hello World";
    }


    @GetMapping("jobs/all")
    public List<Job> findAll()
    {
        return jobRepo.findAll();
    }

 @PostMapping("/createJob")
    public Job create(@RequestBody Job job)
    {
    	
        return jobRepo.save(job);
    }

    @DeleteMapping("recruiter/my_jobs/{id}/{user_id}")
     public List<Job> delete(@PathVariable("id") Long id,@PathVariable("user_id") Long user_id)

     {
         jobRepo.deleteById(id);
         return null;
     }

     @GetMapping("/recruiter/my_jobs/{id}")
     public List<Job>  getMyJobs(@PathVariable("id") Long id)
     {
       // List<Job> jobs = jobRepo.findByUserId(id);

        return  null;
     }

 }