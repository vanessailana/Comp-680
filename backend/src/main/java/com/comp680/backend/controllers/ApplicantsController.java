package com.comp680.backend.controllers;

import com.comp680.backend.repositories.ApplicantsRepository;
import com.comp680.backend.repositories.JobsRepository;
import com.comp680.backend.repositories.UsersRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import javax.validation.Valid;
import com.comp680.backend.models.Applicant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders="*", maxAge = 3600)
public class ApplicantsController {

    @Autowired 
    ApplicantsRepository applicantsRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    JobsRepository jobsRepository;
    
    @PostMapping("/apply")
    public Applicant apply(@RequestBody Applicant applicant ){
        boolean exists = jobsRepository.existsById(applicant.getJob().getId());
        System.out.println("EXISTS JOB: " + exists);
        return new Applicant();
    }
}